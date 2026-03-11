package com.technocenter.productservice.domain.dto.req

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ReqUpdateProductDto(
    @field:NotBlank(message = "name is required")
    val name: String,

    @field:NotNull(message = "price is required")
    @field:Min(value = 1, message = "price must be greater than 0")
    val price: Long,

    @field:NotNull(message = "stock is required")
    @field:Min(value = 1, message = "stock must be at least 1")
    @field:Max(value = 1000, message = "stock must not exceed 1000")
    val stock: Int,

    val categoryId: Int?
)