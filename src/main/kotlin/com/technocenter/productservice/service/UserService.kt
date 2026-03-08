package com.technocenter.productservice.service

import com.technocenter.productservice.domain.dto.res.ResGetUserDetailDto

interface UserService {
    fun getUserById(id: Int): ResGetUserDetailDto
}