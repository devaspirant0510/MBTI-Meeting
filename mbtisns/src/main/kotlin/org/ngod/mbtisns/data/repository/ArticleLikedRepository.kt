package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.ArticleLiked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleLikedRepository :JpaRepository<ArticleLiked,Long>{
    fun findAllByArticle_Id(id:Long):List<ArticleLiked>
}