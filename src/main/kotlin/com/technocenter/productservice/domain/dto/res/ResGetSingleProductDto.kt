package com.technocenter.productservice.domain.dto.res

import java.io.Serializable

data class ResGetSingleProductDto(
    val productId: Int,
    val name: String,
    val stock: Int,
    val price: Long,
    val categoryName: String? = null,
    val createdBy: String? = null,
): Serializable
