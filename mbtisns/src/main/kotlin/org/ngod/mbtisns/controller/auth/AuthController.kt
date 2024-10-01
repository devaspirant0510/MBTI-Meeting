package org.ngod.mbtisns.controller.auth

import io.jsonwebtoken.Jwts
import io.swagger.v3.oas.annotations.headers.Header
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(val service: AuthService) : AuthSwagger {
    override fun getArticle(): String {
        return "a"
    }

    @PostMapping()
    override fun joinAccount(@RequestBody account: Account): Account {
        try {
            return service.accountSave(account)
        } catch (e: Exception) {
            println(e)
            throw Error(e.toString())
        }
    }

    @GetMapping
    fun testUser(@RequestHeader("Authorization") token: String): Boolean {
        print(token)
        return try {
            println(token)
            val claims = Jwts.parser().setSigningKey("9WGDse5arJHVlkO3dIyOC1fBHYoey7cFE2rz+vgSbsDtB5VkMZJTQcE5HhX43HherMq1iUkr+QujQMp82l3xdA==".toByteArray()).parseClaimsJws(token)
            println(claims)
            println(claims.body)
            println(claims.header)
            println(claims.signature)



            true
        } catch (e: Exception) {
            print(e)
            false
        }
    }

}