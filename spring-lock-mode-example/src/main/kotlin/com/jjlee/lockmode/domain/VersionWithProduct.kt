package com.jjlee.lockmode.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Version


@Entity
@Table(name = "VersionWithProduct")
class VersionWithProduct(
    name: String,
    count: Int
) : UlidPrimaryKeyEntity() {

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "count", nullable = false)
    var count: Int = count
        protected set

    @Version
    @Column(name = "\"Version\"", nullable = false)
    var version: Long? = null
        protected set

    fun decrease() {
        validateStockCount()
        this.count -= 1
    }

    private fun validateStockCount() {
        if (this.count < 1) {
            throw IllegalStateException("Not enough stock")
        }
    }
}