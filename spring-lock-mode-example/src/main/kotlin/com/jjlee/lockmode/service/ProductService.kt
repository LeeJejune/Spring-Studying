package com.jjlee.lockmode.service

import com.jjlee.lockmode.domain.repository.ProductRepository
import com.jjlee.lockmode.domain.repository.VersionWithProductRepository
import com.jjlee.lockmode.redis.DistributedLock
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val versionWithProductRepository: VersionWithProductRepository
) {

    @Transactional
    fun updateProductCountWithNoLock(productId: String) {
        val product = productRepository.findByIdOrNull(productId) ?: throw IllegalArgumentException("Product not found")
        product.decrease()

    }

    @Transactional
    fun updateProductCountWithOptimisticLock(productId: String) {
        val product = versionWithProductRepository.findByIdOrNull(productId) ?: throw IllegalArgumentException("Product not found")
        product.decrease()
        versionWithProductRepository.save(product)
    }

    @Transactional
    fun updateProductCountWithPessimisticLock(productId: String) {
        val product = productRepository.findReadLockById(productId) ?: throw IllegalArgumentException("Product not found")
        product.decrease()
    }

    @DistributedLock(key = "#productId")
    fun updateProductCountWithDistributedLock(productId: String) {
        val product = productRepository.findByIdOrNull(productId) ?: throw IllegalArgumentException("Product not found")
        product.decrease()
    }
}