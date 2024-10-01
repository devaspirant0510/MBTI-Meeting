package org.ngod.mbtisns.service

import io.jsonwebtoken.Jwts
import lombok.extern.slf4j.Slf4j
import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.data.repository.AuthRepository
import org.ngod.mbtisns.data.repository.MemberRepository
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Slf4j
@Service
class MemberService(val repository: MemberRepository, val authRepository: AuthRepository) {
    fun checkMemberAuthorization(token:String):Boolean{
        return try{
            val claims = Jwts.parser().setSigningKey("wlswkflarhkwmqtpdusdhzld".toByteArray()).parseClaimsJws(token)
            print(claims)
            print(claims.body)
            print(claims.header)
            print(claims.signature)



            true
        }catch (e:Exception){
            false
        }

    }
    fun memberSave(memberDTO: JoinMemberDTO) : Member {
        val member = memberDTO.member
        val account = authRepository.findById(memberDTO.accountId)
        if(account.isEmpty){
            throw IllegalStateException("계정정보 오류 : 계정이 존재하지 않습니다.")
        }
        member.account =account.get()
        return repository.save(member)
    }
}