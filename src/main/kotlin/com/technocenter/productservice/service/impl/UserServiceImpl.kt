package com.technocenter.productservice.service.impl

import com.technocenter.productservice.domain.dto.res.ResGetUserDetailDto
import com.technocenter.productservice.rest.UserManagementClient
import com.technocenter.productservice.service.UserService
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userManagementClient: UserManagementClient
): UserService {
    //LOOKUP KE REDIS getUserById::1
    //Jika tidak ada maka hit user-service
    //Jika ada dan berhasil maka simpan data di redis
    @Cacheable("getUserById", key = "#id")
    override fun getUserById(id: Int): ResGetUserDetailDto {
        //hit user management service untuk get user by id
        val user = userManagementClient.getUserById(id).body!!.data!!
        //mapping return
        return ResGetUserDetailDto(
            userId = user.userId,
            fullName = user.fullName,
            roleName = user.roleName
        )
    }
}