package com.jjlee.lockmode

import com.jjlee.lockmode.domain.VersionWithProduct
import com.jjlee.lockmode.domain.repository.VersionWithProductRepository
import com.jjlee.lockmode.service.ProductService
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.dao.OptimisticLockingFailureException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class OptimisticLockTest(
    private val versionWithProductRepository: VersionWithProductRepository,
    private val productService: ProductService
) : DefaultTest() {

    init {
        test("updateProductCountWithOptimisticLock : 낙관적 락 적용 테스트") {
            // 데이터베이스에 초기 데이터 저장
            var counting = 0
            val product = versionWithProductRepository.save(VersionWithProduct("Product", 100))

            // 동시성을 테스트하기 위한 설정
            val numberOfThreads = 100
            val executorService = Executors.newFixedThreadPool(numberOfThreads)
            val latch = CountDownLatch(numberOfThreads)

            for (i in 0 until numberOfThreads) {
                executorService.submit {
                    try {
                        // 동시성 업데이트 호출
                        productService.updateProductCountWithOptimisticLock(product.id)
                    } catch (e: OptimisticLockingFailureException) {
                        counting++
                    } finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()
            executorService.shutdown()

            // 최종적으로 저장된 제품 상태 확인
            val updatedProduct = versionWithProductRepository.findById(product.id).get()
            println("잔여 프로덕트 갯수 = ${updatedProduct.count}")
            println("낙관적 락 실패 횟수 = $counting")
            updatedProduct.count shouldNotBe  0 // ObjectOptimisticLockingFailureException 발생
        }
    }
}

