package com.technocenter.productservice.controller

import com.technocenter.productservice.domain.dto.BaseResponse
import com.technocenter.productservice.domain.dto.req.ReqCreateProductDto
import com.technocenter.productservice.domain.dto.res.ResCreateProductDto
import com.technocenter.productservice.domain.dto.res.ResGetSingleProductDto
import com.technocenter.productservice.exception.GeneralException
import com.technocenter.productservice.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/{id}")
    fun getProductById(
        @PathVariable id: Int
    ): ResponseEntity<BaseResponse<ResGetSingleProductDto>>{
        return ResponseEntity.ok(
            BaseResponse(
                data = productService.getProductById(id),
                message = "Success get product"
            )
        )
    }

    @PostMapping
    fun createProduct(
        @RequestHeader("X-ROLE") role: String,
        @Valid @RequestBody req: ReqCreateProductDto
    ): ResponseEntity<BaseResponse<ResCreateProductDto>>{
        if (role != "admin") throw GeneralException(HttpStatus.FORBIDDEN, "Access denied")
        return ResponseEntity(
            BaseResponse(
                message = "Success create product",
                data = productService.createProduct(req),
                status = 201
            ),
            HttpStatus.CREATED
        )
    }

}