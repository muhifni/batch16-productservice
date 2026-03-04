package com.technocenter.productservice.rest

import com.technocenter.productservice.domain.dto.BaseResponse
import com.technocenter.productservice.domain.dto.res.ResGetUserDetailDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "usermanagementservice") //localhost:8081
interface UserManagementClient {
//    //GET USER BY ID
//    @GetMapping("/user-management/api/user/{userId}")
//    fun getUserById(
//        @PathVariable userId: Int
//    ): ResponseEntity<BaseResponse<ResGetUserDetailDto>>

    //localhost:8081/user-management/api/user/{id}
    @GetMapping("/user-management/api/user/{userId}")
    fun getUserById(
        @PathVariable userId: Int
    ): ResponseEntity<BaseResponse<ResGetUserDetailDto>>
}