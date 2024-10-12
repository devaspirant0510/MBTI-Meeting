package org.ngod.mbtisns.domain.service

import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.repository.AuthRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(val repository: AuthRepository) {
    fun findAuthById(accountId:Long):Account{
        val findAuth = repository.findById(accountId).orElseThrow {
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(),"계정을 찾을수 없습니다.")
        }
        return findAuth

    }
    fun accountSave(account: Account): Account {
        return repository.save(account);
    }

    fun readLoginUser(uid: String): Account {
        val findUser = repository.findByUid(uid)
        if(findUser.isEmpty){
            throw IllegalStateException("유저를 찾는데 실패했습니다.")
        }
        return findUser.get()

    }
}