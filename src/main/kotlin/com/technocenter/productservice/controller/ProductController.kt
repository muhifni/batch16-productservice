package com.technocenter.productservice.controller

import com.technocenter.productservice.domain.dto.BaseResponse
import com.technocenter.productservice.domain.dto.res.ResGetSingleProductDto
import com.technocenter.productservice.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

}