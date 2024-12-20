package com.jjlee.lockmode

import com.jjlee.lockmode.domain.Product
import com.jjlee.lockmode.domain.repository.ProductRepository
import com.jjlee.lockmode.service.ProductService
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class NoLockTest(
    private val productRepository: ProductRepository,
    private val productService: ProductService
) : DefaultTest() {
    init {
        test("updateProductCountWithNoLock : 락 미적용 호출 테스트") {
            val product = productRepository.save(Product("Product", 100))

            val numberOfThreads = 100
            val executorService = Executors.newFixedThreadPool(numberOfThreads)
            val latch = CountDownLatch(numberOfThreads)

            for (i in 0 until numberOfThreads) {
                executorService.submit {
                    try {
                        productService.updateProductCountWithNoLock(product.id)
                    } finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()
            executorService.shutdown()

            val updatedProduct = productRepository.findById(product.id).get()
            println("잔여 프로덕트 갯수 = ${updatedProduct.count}")
            updatedProduct.count shouldNotBe 0
        }
    }
}