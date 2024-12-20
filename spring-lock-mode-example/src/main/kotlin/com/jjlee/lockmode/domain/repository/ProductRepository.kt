package com.jjlee.lockmode.domain.repository

import com.jjlee.lockmode.domain.Product
import jakarta.persistence.LockModeType
import jakarta.persistence.QueryHint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.QueryHints

interface ProductRepository : JpaRepository<Product, String> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints(QueryHint(name = "jakarta.persistence.lock.timeout", value = "10000"))
    fun findReadLockById(productId: String): Product?
}