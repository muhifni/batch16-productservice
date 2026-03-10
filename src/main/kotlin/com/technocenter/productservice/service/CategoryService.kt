package com.technocenter.productservice.service

import com.technocenter.productservice.domain.dto.req.ReqCreateCategoryDto
import com.technocenter.productservice.domain.dto.res.ResGetCategoryDto

interface CategoryService {
    fun createCategory(req: ReqCreateCategoryDto): ResGetCategoryDto
    fun getAllCategories(): List<ResGetCategoryDto>
}