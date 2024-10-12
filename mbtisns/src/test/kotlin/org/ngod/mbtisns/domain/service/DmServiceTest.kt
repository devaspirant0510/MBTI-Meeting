package org.ngod.mbtisns.domain.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.Dm
import org.ngod.mbtisns.data.repository.AuthRepository
import org.ngod.mbtisns.data.repository.DmRepository
import org.ngod.mbtisns.data.repository.DmRoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Transactional
class DmServiceTest {
    private lateinit var dmService: DmService
    @Autowired
    private lateinit var dmRepository: DmRepository
    @Autowired
    private lateinit var dmRoomRepository: DmRoomRepository
    @Autowired
    private lateinit var authRepository: AuthRepository

    @BeforeEach
    fun setUp() {
//        dmService = DmService(dmRepository,dmRoomRepository,authRepository)
    }

    @Test
    fun createRoom() {
        val account1 = authRepository.save(Account(uid = "a", email = "a", provider = "kakao"))
        val account2 = authRepository.save(Account(uid = "a", email = "a", provider = "kakao"))
        Assertions.assertThat(authRepository.findAll().size).isEqualTo(2)
        val userList = listOf(account1.id!!,account2.id!!)
        println(userList)
        val dmRoom = dmService.createRoom(userList)
        println(dmRoom)
    }
}