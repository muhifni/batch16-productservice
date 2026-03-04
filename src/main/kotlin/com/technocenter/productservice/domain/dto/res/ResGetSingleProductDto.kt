package com.technocenter.productservice.domain.dto.res

data class ResGetSingleProductDto(
    val productId: Int,
    val name: String,
    val stock: Int,
    val price: Long,
    val createdBy: String? = null
)
