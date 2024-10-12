package org.ngod.mbtisns.controller.article

import org.ngod.mbtisns.controller.article.dto.RequestArticleDTO
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.core.ApiResponse
import org.ngod.mbtisns.data.entity.Article
import org.ngod.mbtisns.data.entity.ArticleImage
import org.ngod.mbtisns.data.entity.ArticleLiked
import org.ngod.mbtisns.data.entity.ProfileImage
import org.ngod.mbtisns.data.entity.enum.FileType
import org.ngod.mbtisns.domain.service.ArticleService
import org.ngod.mbtisns.domain.service.AuthService
import org.ngod.mbtisns.domain.service.FileService
import org.ngod.mbtisns.domain.service.JwtService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/article")
class ArticleController(
    private val service: ArticleService,
    private val jwtService: JwtService,
    private val fileService: FileService,
    private val authService: AuthService
) : ArticleSwagger {

    @PostMapping(consumes = ["multipart/form-data"])
    override fun postOneArticle(
        @RequestHeader("Authorization") authorization: String,
        @ModelAttribute requestArticleDTO: RequestArticleDTO
    ): ApiResponse<Article> {
        val jwtUser = jwtService.verifyToken(authorization) ?: throw ApiException(HttpStatus.UNAUTHORIZED.value(),"인증되지 않은 유저")
        val userUid = jwtUser.sub
        val writer = authService.readLoginUser(userUid)
        val result = requestArticleDTO.images?.let {
            val article = Article(content = requestArticleDTO.content, account = writer)
            val savedArticle = service.createArticle(article)
            val imageList = it.map { image ->
                fileService.saveFileArticle(image,savedArticle)
            }
            article.images!!.addAll(imageList)
            val imageUpdatedArticle = service.createArticle(article)
            return@let imageUpdatedArticle
        } ?: run {
            val article = Article(content = requestArticleDTO.content, account = writer)
            val savedArticle = service.createArticle(article)
            return@run savedArticle
        }
        return ApiResponse.success(result)
    }

    @GetMapping("/{articleId}")
    override fun findOneArticle(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "articleId")
        articleId: Long
    ): ApiResponse<Article> {
        println("get article $articleId")
        return ApiResponse.success(service.findOneArticleById(articleId))
    }


    @PatchMapping("{articleId}/like/{accountId}")
    override fun voteForArticle(
        @PathVariable articleId: Long,
        @PathVariable accountId: Long,
        @RequestHeader("Authorization")
        authorization: String,
    ): ApiResponse<ArticleLiked> {
        println("patch method ")
        return ApiResponse.success(service.updateArticleLiked(authorization, articleId, accountId))
    }

    @GetMapping("/like/{articleId}")
    override fun findAllLikedAtArticle(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "articleId")
        articleId: Long
    ): ApiResponse<List<ArticleLiked>> {
        return ApiResponse.success(service.findAllLikedAtArticleId(articleId))
    }

    @GetMapping()
    override fun findAllArticle(
        @RequestHeader("Authorization")
        authorization: String
    ): ApiResponse<List<Article>> {
        jwtService.verifyToken(authorization)
        return ApiResponse.success(service.findAllArticle())
    }

    @GetMapping("/paging")
    override fun findAllArticle(
        @RequestHeader("Authorization")
        authorization: String,
        @RequestParam()
        page: Int,
        @RequestParam()
        size: Int,
    ): ApiResponse<List<Article>> {
        jwtService.verifyToken(authorization)
        return ApiResponse.success(service.findAllArticle(page, size).toList())
    }

}