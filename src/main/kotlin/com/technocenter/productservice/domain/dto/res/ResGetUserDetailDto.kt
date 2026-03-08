package com.technocenter.productservice.domain.dto.res

import java.io.Serializable

data class ResGetUserDetailDto(
    val userId: Int,
    val fullName: String,
    val roleName: String
): Serializable