package com.jjlee.lockmode.domain.repository

import com.jjlee.lockmode.domain.VersionWithProduct
import org.springframework.data.jpa.repository.JpaRepository

interface VersionWithProductRepository : JpaRepository<VersionWithProduct, String> {
}