package org.ngod.mbtisns.service

import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.repository.AuthRepository
import org.springframework.stereotype.Service

@Service
class AuthService(val repository:AuthRepository) {
    fun accountSave(account:Account):Account{
        return repository.save(account);
    }
}