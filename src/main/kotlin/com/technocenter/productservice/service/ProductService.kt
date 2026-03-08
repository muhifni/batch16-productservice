package com.technocenter.productservice.service

import com.technocenter.productservice.domain.dto.req.ReqCreateProductDto
import com.technocenter.productservice.domain.dto.res.ResGetSingleProductDto

interface ProductService {
    fun getProductById(id: Int): ResGetSingleProductDto
    fun createProduct(req: ReqCreateProductDto): ResGetSingleProductDto
}