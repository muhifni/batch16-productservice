package com.technocenter.productservice.domain.dto.req

import jakarta.validation.constraints.NotBlank

data class ReqCreateCategoryDto(
    @field:NotBlank(message = "name is required")
    val name: String
)