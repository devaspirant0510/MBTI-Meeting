package org.ngod.mbtisns.domain.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.data.entity.enum.Mbti
import org.ngod.mbtisns.data.repository.AuthRepository
import org.ngod.mbtisns.data.repository.FollowRepository
import org.ngod.mbtisns.data.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Transactional
class MemberServiceTest {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var authRepository: AuthRepository

    @Autowired
    private lateinit var followRepository: FollowRepository

    private lateinit var memberService: MemberService

    @BeforeEach
    fun setUp() {
        memberService = MemberService(memberRepository, authRepository, followRepository)
    }

    @Test
    fun `계정이 저장되는지 확인`() {
        val account = Account(id = 1L, email = "adfs", provider = "kakao", uid = "asdf")
        authRepository.save(account)
        Assertions.assertThat(authRepository.findAll().size).isEqualTo(1)

    }

    @Test
    fun `멤버가 저장되는지 확인`() {
        val account = Account(email = "adfs", provider = "kakao", uid = "asdf")
        val savedAccount = authRepository.save(account)
        val member = Member(mbti = Mbti.ENFJ, nickName = "asdf",)
        val savedMember = memberRepository.save(member)
        Assertions.assertThat(memberRepository.findAll().size).isEqualTo(1)
        Assertions.assertThat(authRepository.findAll().size).isEqualTo(1)
    }

    @Test
    fun followMember() {
        val account1 = Account(email = "adfs", provider = "kakao", uid = "asdf")
        val savedAccount1 = authRepository.save(account1)
        val member1 = Member(mbti = Mbti.ENFJ, nickName = "asdf")
        memberRepository.save(member1)
        val account2 = Account(email = "adfs", provider = "kakao", uid = "asdf")
        val savedAccount2 = authRepository.save(account2)
        val member2 = Member(mbti = Mbti.ENFJ, nickName = "asdf")
        memberRepository.save(member2)
        Assertions.assertThat(memberRepository.findAll().size).isEqualTo(2)
        Assertions.assertThat(authRepository.findAll().size).isEqualTo(2)
        memberService.followUser(member1.id!!, member2.id!!)
        println(memberService.getMemberById(member1.id!!))
        Assertions.assertThat(followRepository.findAll().size).isEqualTo(1)
        Assertions.assertThat(memberService.getMemberById(member1.id!!).followings?.size).isEqualTo(1)


    }
}