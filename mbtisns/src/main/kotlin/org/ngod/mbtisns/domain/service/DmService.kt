package org.ngod.mbtisns.domain.service

import org.ngod.mbtisns.controller.dm.dto.SendDmDto
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.data.entity.AccountDmRoom
import org.ngod.mbtisns.data.entity.Dm
import org.ngod.mbtisns.data.entity.DmRoom
import org.ngod.mbtisns.data.entity.enum.DmRoleType
import org.ngod.mbtisns.data.repository.AccountDmRoomRepository
import org.ngod.mbtisns.data.repository.AuthRepository
import org.ngod.mbtisns.data.repository.DmRepository
import org.ngod.mbtisns.data.repository.DmRoomRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class DmService(
    private val dmRepository: DmRepository,
    private val accountDmRoomRepository: AccountDmRoomRepository,
    private val dmRoomRepository: DmRoomRepository,
    private val authService: AuthService
) {
    fun findRoomById(roomId: Long): DmRoom {
        val foundDmRoom = dmRoomRepository.findById(roomId)
        if (foundDmRoom.isEmpty) {
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(), "존재하지 않는 dm room 입니다")
        }
        return foundDmRoom.get()
    }

    fun createRoom(list: List<Long>): DmRoom {
        val ownerAccount = authService.findAuthById(list[0])
        val dmRoom = DmRoom(roomName = "")
        val savedDmRoom = dmRoomRepository.save(dmRoom)
        val owner = AccountDmRoom(account = ownerAccount, role = DmRoleType.OWNER, dmRoom = savedDmRoom)
        val participationAccount = authService.findAuthById(list[1])
        val participation =
            AccountDmRoom(account = participationAccount, role = DmRoleType.PARTICIPATE, dmRoom = savedDmRoom)
        accountDmRoomRepository.save(owner)
        accountDmRoomRepository.save(participation)
        print(savedDmRoom)
        return savedDmRoom
    }

    fun sendMessage(data: SendDmDto): Dm {
        val sender = authService.findAuthById(data.senderId)
        val dmRoom = findRoomById(data.dmRoomId)

        val dm = Dm(message = data.message, messageType = data.messageType, sender = sender, dmRoom = dmRoom)
        return dmRepository.save(dm)
    }

    fun findAllDmByRoomId(roomId: Long): List<Dm> {
        return dmRepository.findAllByDmRoomId(roomId)
    }

    fun findAllDmByRoomIdAtPage(roomId: Long, page: Int, size: Int): Page<Dm> {
        val pageRequest = PageRequest.of(page, size)
        return dmRepository.findAllByDmRoomId(roomId, pageRequest)
    }

    fun findAllDmRoomByUserId(accountId: Long): List<DmRoom> {
        val accountDmRoom = accountDmRoomRepository.findAllByAccountId(accountId)
        val dmRoomList = mutableListOf<Long>()
        accountDmRoom.forEach {
            dmRoomList.add(it.id!!)
        }
        return dmRoomRepository.findAllById(dmRoomList)
    }

}