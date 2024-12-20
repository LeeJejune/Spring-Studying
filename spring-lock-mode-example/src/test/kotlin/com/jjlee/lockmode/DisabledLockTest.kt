package com.jjlee.lockmode

import com.jjlee.lockmode.domain.Product
import com.jjlee.lockmode.domain.repository.ProductRepository
import com.jjlee.lockmode.service.ProductService
import io.kotest.matchers.shouldBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class DisabledLockTest(
    private val productRepository: ProductRepository,
    private val productService: ProductService
) : DefaultTest() {
    init {
        test("updateProductCountWithPessimisticLock : 분산 락 적용 테스트") {
            // 데이터베이스에 초기 데이터 저장
            val product = productRepository.save(Product("Product", 100))

            // 동시성을 테스트하기 위한 설정
            val numberOfThreads = 100
            val executorService = Executors.newFixedThreadPool(numberOfThreads)
            val latch = CountDownLatch(numberOfThreads)

            for (i in 0 until numberOfThreads) {
                executorService.submit {
                    try {
                        // 동시성 업데이트 호출
                        productService.updateProductCountWithDistributedLock(product.id)
                    } finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()
            executorService.shutdown()

            // 최종적으로 저장된 제품 상태 확인
            val updatedProduct = productRepository.findById(product.id).get()
            println("잔여 프로덕트 갯수 = ${updatedProduct.count}")
            updatedProduct.count shouldBe 0
        }
    }
}