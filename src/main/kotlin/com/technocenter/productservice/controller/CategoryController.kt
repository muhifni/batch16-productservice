package com.technocenter.productservice.controller

import com.technocenter.productservice.domain.dto.BaseResponse
import com.technocenter.productservice.domain.dto.req.ReqCreateCategoryDto
import com.technocenter.productservice.domain.dto.res.ResGetCategoryDto
import com.technocenter.productservice.exception.GeneralException
import com.technocenter.productservice.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/category")
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun createCategory(
        @RequestHeader("X-ROLE") role: String,
        @Valid @RequestBody req: ReqCreateCategoryDto
    ): ResponseEntity<BaseResponse<ResGetCategoryDto>> {
        if (role != "admin") throw GeneralException(HttpStatus.FORBIDDEN, "Access denied")
        return ResponseEntity(
            BaseResponse(
                message = "Success create category",
                data = categoryService.createCategory(req),
                status = 201
            ),
            HttpStatus.CREATED
        )
    }

    @GetMapping
    fun getAllCategories(): ResponseEntity<BaseResponse<List<ResGetCategoryDto>>> {
        return ResponseEntity.ok(
            BaseResponse(
                message = "Success",
                data = categoryService.getAllCategories()
            )
        )
    }
}