package com.technocenter.productservice.repository

import com.technocenter.productservice.domain.entity.MasterProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasterProductRepository: JpaRepository<MasterProductEntity, Int> {
}