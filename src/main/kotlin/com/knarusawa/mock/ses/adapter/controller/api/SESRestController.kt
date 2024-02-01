package com.knarusawa.mock.ses.adapter.controller.api

import com.knarusawa.mock.ses.application.dto.MailListDto
import com.knarusawa.mock.ses.application.service.batchClearMail.BatchClearMailService
import com.knarusawa.mock.ses.application.service.clearMail.ClearMailService
import com.knarusawa.mock.ses.application.service.query.MailDtoQueryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class SESRestController(
        private val mailDtoQueryService: MailDtoQueryService,
        private val clearMailService: ClearMailService,
        private val batchClearMailService: BatchClearMailService
) {
    @GetMapping("/store")
    @ResponseStatus(HttpStatus.OK)
    fun storePost(
            @RequestParam since: Long?,
            @RequestParam to: Long?
    ): MailListDto {
        return mailDtoQueryService.findByAtBetween(since, to)
    }

    @GetMapping("/emails")
    @ResponseStatus(HttpStatus.OK)
    fun emailsGet(
            @RequestParam(value = "to_address") toAddress: String?,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") size: Int,
    ): MailListDto {
        return mailDtoQueryService.findByToAddress(toAddress = toAddress, page = page, size = size)
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun clearPost() {
        clearMailService.exec()
    }

    @DeleteMapping("/batch-clear")
    @ResponseStatus(HttpStatus.OK)
    fun batchClearDelete(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "0") size: Int,
            @RequestParam(defaultValue = "300") seconds: Int
    ): Int {
        return batchClearMailService.exec(
                page = page,
                size = size,
                seconds = seconds
        )
    }
}