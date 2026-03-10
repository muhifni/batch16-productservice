package com.technocenter.productservice.service.impl

import com.technocenter.productservice.domain.constant.ConstantVariable
import com.technocenter.productservice.domain.dto.req.ReqCreateProductDto
import com.technocenter.productservice.domain.dto.res.ResCreateProductDto
import com.technocenter.productservice.domain.dto.res.ResGetSingleProductDto
import com.technocenter.productservice.domain.dto.res.ResGetUserDetailDto
import com.technocenter.productservice.domain.entity.MasterProductEntity
import com.technocenter.productservice.exception.DataNotFoundException
import com.technocenter.productservice.repository.MasterProductRepository
import com.technocenter.productservice.rest.UserManagementClient
import com.technocenter.productservice.service.ProductService
import com.technocenter.productservice.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: MasterProductRepository,
    private val userManagementClient: UserManagementClient,
    private val httpServletRequest: HttpServletRequest,
    private val userService: UserService
): ProductService {
    override fun getProductById(id: Int): ResGetSingleProductDto {
        val userId = httpServletRequest.getHeader("X-USER-ID")

        //STEP 1 -> GET PRODUCT DI DB BY ID PRODUCT
        val product = productRepository.findById(id).orElseThrow {
            DataNotFoundException("Product with id $id not found")
        }

        //STEP 2 -> GET USER BY ID (product.created_by)
        var user: ResGetUserDetailDto? = null
        if(product.createdBy != null){
            user = userService.getUserById(product.createdBy!!.toInt())
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

    override fun createProduct(req: ReqCreateProductDto): ResCreateProductDto {
        val product = MasterProductEntity(
            name = req.name,
            price = req.price,
            stock = req.stock
        )
        //STEP 1 AMBIL HEADER
        val userId = httpServletRequest.getHeader(ConstantVariable.HEADER_USER_ID)!!
        product.createdBy = userId
        val productDb = productRepository.save(product)

        return ResCreateProductDto(
            productId = productDb.id!!,
            name = productDb.name,
            price = productDb.price,
            stock = productDb.stock,
//            categoryName = productDb.
        )
    }

    override fun deleteUserProduct(userId: Int) {
        val userProductList = productRepository.findByUserId(userId)
        userProductList.forEach { product ->
            product.isDelete = true
        }
        productRepository.saveAll(userProductList)
    }
}