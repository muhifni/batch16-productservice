package com.technocenter.productservice.domain.dto.res

data class ResCreateProductDto (
    val productId: Int,
    val name: String,
    val price: Long,
    val stock: Int,
    val categoryName: String? = null
)