package com.technocenter.productservice.service.impl

import com.technocenter.productservice.domain.dto.req.ReqCreateCategoryDto
import com.technocenter.productservice.domain.dto.res.ResGetCategoryDto
import com.technocenter.productservice.domain.entity.MasterCategoryEntity
import com.technocenter.productservice.repository.MasterCategoryRepository
import com.technocenter.productservice.service.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    private val categoryRepository: MasterCategoryRepository
) : CategoryService {

    override fun createCategory(req: ReqCreateCategoryDto): ResGetCategoryDto {
        val category = MasterCategoryEntity(name = req.name)
        val saved = categoryRepository.save(category)
        return ResGetCategoryDto(
            id = saved.id!!,
            name = saved.name
        )
    }

    override fun getAllCategories(): List<ResGetCategoryDto> {
        return categoryRepository.findAll().map {
            ResGetCategoryDto(
                id = it.id!!,
                name = it.name
            )
        }
    }
}