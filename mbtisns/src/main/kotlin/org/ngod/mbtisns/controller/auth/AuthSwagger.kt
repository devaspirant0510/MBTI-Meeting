package org.ngod.mbtisns.controller.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.ngod.mbtisns.data.entity.Account

@Tag(name = "Account Api", description = "유저 관련 API")
interface AuthSwagger {
    @Operation(summary = "게시글 불러오기")
    @ApiResponses(value = [ApiResponse(responseCode = "404", description = "not found error")])
    fun getArticle(): String

    @Operation(summary = "게시글 불러오기")
    @ApiResponses(value = [ApiResponse(responseCode = "404", description = "not found error")])
    fun joinAccount(account: Account): Account

}