package org.ngod.mbtisns.controller.dm

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.ngod.mbtisns.controller.dm.dto.CreateRoomDto
import org.ngod.mbtisns.controller.dm.dto.SendDmDto
import org.ngod.mbtisns.core.ApiResponse
import org.ngod.mbtisns.data.entity.Dm
import org.ngod.mbtisns.data.entity.DmRoom
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "Dm Api", description = "DM 관련 API")
interface DmSwagger {
    @Operation(summary = "채팅방 생성", description = "1대1 dm 으로 채팅방 생성")
    fun createRoom(data: CreateRoomDto): ApiResponse<DmRoom>
    @Operation(summary = "채팅방 메시지 보내기", description = "상대에게 dm 메시지 전송")
    fun sendDm(roomId:Long,authorization:String,data:SendDmDto):ApiResponse<Dm>
    @Operation(summary = "채팅방 dm 불러오기", description = "채팅방의 dm 전체 불러오기")
    fun readAllDmByRoomId(roomId: Long,authorization: String):ApiResponse<List<Dm>>
    @Operation(summary = "채팅방 dm 불러오기", description = "채팅방의 dm 전체 불러오기")
    fun readAllDmByRoomIdPage(
        roomId: Long,
        page:Int,
        size:Int,
        authorization: String):ApiResponse<List<Dm>>
    fun readAllDmRoomByUserId(
        accountId:Long,
        authorization: String):ApiResponse<List<DmRoom>>

}