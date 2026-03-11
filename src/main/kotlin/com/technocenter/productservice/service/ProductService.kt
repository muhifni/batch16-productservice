package com.technocenter.productservice.service

import com.technocenter.productservice.domain.dto.req.ReqCreateProductDto
import com.technocenter.productservice.domain.dto.req.ReqUpdateProductDto
import com.technocenter.productservice.domain.dto.res.ResCreateProductDto
import com.technocenter.productservice.domain.dto.res.ResGetSingleProductDto

interface ProductService {
    fun getProductById(id: Int): ResGetSingleProductDto
    fun getListProduct(): List<ResGetSingleProductDto>
    fun createProduct(req: ReqCreateProductDto): ResCreateProductDto
    fun updateProduct(id: Int, req: ReqUpdateProductDto): ResGetSingleProductDto
    fun softDeleteProduct(id: Int): ResGetSingleProductDto
    fun deleteUserProduct(userId: Int)
}