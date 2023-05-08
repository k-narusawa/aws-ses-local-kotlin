package com.knarusawa.mock.ses.sesmock.controller

import com.knarusawa.mock.ses.sesmock.service.MailService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/")
class SESController(
    private val mailService: MailService
) {
    @GetMapping
    fun index(
        model: Model,
        @RequestParam(name = "to") to: String? = null,
    ): String {
        val entities = mailService.getEmails(page = 0, size=10, to = to)
        model.addAttribute("mails", entities.emails)
        return "index"
    }
}