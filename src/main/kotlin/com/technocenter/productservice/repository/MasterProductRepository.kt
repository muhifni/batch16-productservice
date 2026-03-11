package com.technocenter.productservice.repository

import com.technocenter.productservice.domain.entity.MasterProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MasterProductRepository: JpaRepository<MasterProductEntity, Int> {
    @Query("""
        SELECT P FROM MasterProductEntity P
        WHERE P.createdBy = :userId
        AND P.isDelete = false
    """, nativeQuery = false)
    fun findByUserId(userId: Int): List<MasterProductEntity>

    @Query("""
    SELECT P FROM MasterProductEntity P
    WHERE P.isDelete = false
""", nativeQuery = false)
    fun findAllActive(): List<MasterProductEntity>
}