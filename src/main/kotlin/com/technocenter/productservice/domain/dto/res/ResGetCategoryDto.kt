package com.technocenter.productservice.domain.dto.res

import java.io.Serializable

data class ResGetCategoryDto (
    val categoryId: Int,
    val name: String
): Serializable