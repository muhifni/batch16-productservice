package com.technocenter.productservice.domain.dto.req

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ReqCreateProductDto(
    @field:NotBlank(message = "name is required")
    val name: String,

    @field:NotBlank(message = "price is required")
    val price: Long,

    @field:NotBlank(message = "stock is required")
    @field:Pattern(
        regexp = "^(?:1000|[1-9][0-9]{0,2}|0)\$",
        message = "Stock must be a number between 1 and 1000."
    )
    val stock: Int,
)
