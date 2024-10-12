package org.ngod.mbtisns.domain.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.controller.member.dto.MemberDto
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.Article
import org.ngod.mbtisns.data.entity.enum.Mbti
import org.ngod.mbtisns.data.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Transactional
class ArticleServiceTest {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var authRepository: AuthRepository

    @Autowired
    private lateinit var followRepository: FollowRepository

    @Autowired
    private lateinit var articleRepository: ArticleRepository

    @Autowired
    private lateinit var articleLikedRepository: ArticleLikedRepository

    private lateinit var memberService: MemberService

    private lateinit var articleService: ArticleService

    private lateinit var jwtService: JwtService

    private lateinit var authService: AuthService

    private val masterToken:String = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw"
    @BeforeEach
    fun setUp() {
        authService = AuthService(authRepository)
        jwtService = JwtService()
        memberService = MemberService(memberRepository, authRepository, followRepository)
        articleService = ArticleService(articleRepository,jwtService,authService,articleLikedRepository)
    }

    @Test
    fun writeArticle() {
        val account = authRepository.save(Account(provider = "kakao", email = "aa", uid = "45a25708-e40b-4c8e-983c-0e265bf66533"))
        val member = memberService.memberSave(JoinMemberDTO(accountId = account.id!!, member = MemberDto(nickName = "ads", mbti = Mbti.ENFJ)) )
        val article = articleService.createArticle(Article(content="a", account = account))
        Assertions.assertThat(articleRepository.findAll().size).isEqualTo(1)
        println(article)
    }
    @Test
    fun voteLikeForArticle(){
        val account = authRepository.save(Account(provider = "kakao", email = "aa", uid = "45a25708-e40b-4c8e-983c-0e265bf66533"))
        val member = memberService.memberSave(JoinMemberDTO(accountId = account.id!!, member = MemberDto(nickName = "ads", mbti = Mbti.ENFJ)) )
        val article = articleService.createArticle(Article(content="a", account = account))
        val liked = articleService.updateArticleLiked(masterToken,1,1)
        println(liked)
        val updatedArticle = articleService.findOneArticleById(1)
//        Assertions.assertThat(updatedArticle.likes?.size).isEqualTo(1)


    }
}