package com.technocenter.productservice.service.impl

import com.technocenter.productservice.domain.dto.res.ResGetSingleProductDto
import com.technocenter.productservice.domain.dto.res.ResGetUserDetailDto
import com.technocenter.productservice.exception.DataNotFoundException
import com.technocenter.productservice.repository.MasterProductRepository
import com.technocenter.productservice.rest.UserManagementClient
import com.technocenter.productservice.service.ProductService
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: MasterProductRepository,
    private val userManagementClient: UserManagementClient
): ProductService {
    override fun getProductById(id: Int): ResGetSingleProductDto {
        //STEP 1 -> GET PRODUCT DI DB BY ID PRODUCT
        val product = productRepository.findById(id).orElseThrow {
            DataNotFoundException("Product with id $id not found")
        }

        //STEP 2 -> GET USER BY ID (product.created_by)
        var user: ResGetUserDetailDto? = null
        if(product.createdBy != null){
            user = userManagementClient.getUserById(product.createdBy!!.toInt())
                .body!!.data
        }

        //Mapping response
        return ResGetSingleProductDto(
            productId = product.id!!,
            stock = product.stock,
            name = product.name,
            price = product.price,
            //JIKA USER != NULL MAKA AMBIL user.fullname
            //JIKA USER == NULL maka createdBy = null
            createdBy = user?.fullName,
        )
    }
}