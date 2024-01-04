package com.knarusawa.mock.ses.sesmock.adapter.controller.api

import com.knarusawa.mock.ses.sesmock.application.dto.MailListDto
import com.knarusawa.mock.ses.sesmock.application.service.batchClearMail.BatchClearMailService
import com.knarusawa.mock.ses.sesmock.application.service.clearMail.ClearMailService
import com.knarusawa.mock.ses.sesmock.application.service.getMailList.GetMailListService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class SESRestController(
        private val getMailService: GetMailListService,
        private val clearMailService: ClearMailService,
        private val batchClearMailService: BatchClearMailService
) {
    @GetMapping("/store")
    @ResponseStatus(HttpStatus.OK)
    fun storePost(
            @RequestParam since: String?,
            @RequestParam to: String?
    ): MailListDto {
        return getMailService.exec(since = since, to = to)
    }

    @GetMapping("/emails")
    @ResponseStatus(HttpStatus.OK)
    fun emailsGet(
            @RequestParam to: String?,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") size: Int,
    ): MailListDto {
        return getMailService.exec(
                page = page,
                size = size,
                to = to,
        )
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