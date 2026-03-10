package com.technocenter.productservice.repository

import com.technocenter.productservice.domain.entity.MasterCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasterCategoryRepository: JpaRepository<MasterCategoryEntity, Int>