package com.technocenter.productservice.service.impl

import com.technocenter.productservice.domain.constant.ConstantVariable
import com.technocenter.productservice.domain.dto.req.ReqCreateProductDto
import com.technocenter.productservice.domain.dto.req.ReqUpdateProductDto
import com.technocenter.productservice.domain.dto.res.ResCreateProductDto
import com.technocenter.productservice.domain.dto.res.ResGetSingleProductDto
import com.technocenter.productservice.domain.dto.res.ResGetUserDetailDto
import com.technocenter.productservice.domain.entity.MasterProductEntity
import com.technocenter.productservice.exception.DataNotFoundException
import com.technocenter.productservice.repository.MasterCategoryRepository
import com.technocenter.productservice.repository.MasterProductRepository
import com.technocenter.productservice.rest.UserManagementClient
import com.technocenter.productservice.service.ProductService
import com.technocenter.productservice.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class ProductServiceImpl(
    private val productRepository: MasterProductRepository,
    private val userManagementClient: UserManagementClient,
    private val httpServletRequest: HttpServletRequest,
    private val userService: UserService,
    private val categoryRepository: MasterCategoryRepository
): ProductService {
    @Cacheable("getProductById", key = "#id")
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
            categoryName = product.category?.name,
            //JIKA USER != NULL MAKA AMBIL user.fullname
            //JIKA USER == NULL maka createdBy = null
            createdBy = user?.fullName,
        )
    }

    @Cacheable("getListProduct")
    override fun getListProduct(): List<ResGetSingleProductDto> {
        return productRepository.findAllActive().map { product ->
            var user: ResGetUserDetailDto? = null
            if (product.createdBy != null) {
                user = userService.getUserById(product.createdBy!!.toInt())
            }

            ResGetSingleProductDto(
                productId = product.id!!,
                name = product.name,
                stock = product.stock,
                price = product.price,
                categoryName = product.category?.name,
                createdBy = user?.fullName,
            )
        }
    }

    @CacheEvict("getListProduct", allEntries = true)
    override fun createProduct(req: ReqCreateProductDto): ResCreateProductDto {
        // Cek category ada atau tidak
        val category = categoryRepository.findById(req.categoryId).orElseThrow {
            DataNotFoundException("Category with id ${req.categoryId} not found")
        }

        // Ambil userId dari header
        val userId = httpServletRequest.getHeader(ConstantVariable.HEADER_USER_ID)!!

        // Get user detail untuk ambil fullName
        val user = userService.getUserById(userId.toInt())

        val product = MasterProductEntity(
            name = req.name,
            price = req.price,
            stock = req.stock,
            category = category
        )
        product.createdBy = userId

        val productDb = productRepository.save(product)

        return ResCreateProductDto(
            productId = productDb.id!!,
            name = productDb.name,
            price = productDb.price,
            stock = productDb.stock,
            categoryName = productDb.category?.name,
            createdBy = user.fullName
        )
    }

    @Caching(evict = [
        CacheEvict("getProductById", key = "#id"),
        CacheEvict("getListProduct", allEntries = true)
    ])
    override fun updateProduct(id: Int, req: ReqUpdateProductDto): ResGetSingleProductDto {
        // Cari product
        val product = productRepository.findById(id).orElseThrow {
            DataNotFoundException("Product with id $id not found")
        }

        // Update field
        product.name = req.name
        product.price = req.price
        product.stock = req.stock
        product.updatedBy = httpServletRequest.getHeader(ConstantVariable.HEADER_USER_ID)

        // Update category kalau categoryId dikirim
        if (req.categoryId != null) {
            val category = categoryRepository.findById(req.categoryId).orElseThrow {
                DataNotFoundException("Category with id ${req.categoryId} not found")
            }
            product.category = category
        }

        val updated = productRepository.save(product)

        return ResGetSingleProductDto(
            productId = updated.id!!,
            name = updated.name,
            price = updated.price,
            stock = updated.stock,
            categoryName = updated.category?.name,
            createdBy = updated.createdBy
        )
    }

    @Caching(evict = [
        CacheEvict("getProductById", key = "#id"),
        CacheEvict("getListProduct", allEntries = true)
    ])
    override fun softDeleteProduct(id: Int): ResGetSingleProductDto {
        val product = productRepository.findById(id).orElseThrow {
            DataNotFoundException("Product with id $id not found")
        }

        product.isDelete = true
        product.deletedAt = Timestamp(System.currentTimeMillis())
        product.deletedBy = httpServletRequest.getHeader(ConstantVariable.HEADER_USER_ID)?.toInt()

        val deleted = productRepository.save(product)

        return ResGetSingleProductDto(
            productId = deleted.id!!,
            name = deleted.name,
            price = deleted.price,
            stock = deleted.stock,
            categoryName = deleted.category?.name,
            createdBy = deleted.createdBy
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