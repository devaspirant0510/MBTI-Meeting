package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepository :JpaRepository<Account,Long>{
}