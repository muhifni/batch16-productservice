package com.technocenter.productservice.domain.dto.req

data class ReqCreateProductDto(
    val name: String,
    val price: Long,
    val stock: Int,
)
