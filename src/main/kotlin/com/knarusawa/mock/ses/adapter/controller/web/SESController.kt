package com.knarusawa.mock.ses.adapter.controller.web

import com.knarusawa.mock.ses.application.service.clearMail.ClearMailService
import com.knarusawa.mock.ses.application.service.getMailList.GetMailListService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class SESController(
        private val clearMailService: ClearMailService,
        private val getMailListService: GetMailListService
) {
    @GetMapping("/")
    fun index(
            model: Model,
            @RequestParam(name = "to") to: String? = null,
            @RequestParam(name = "page", defaultValue = "1") page: Int,
            @RequestParam(name = "size", defaultValue = "10") size: Int,
    ): String {
        val entities = getMailListService.exec(page = page - 1, size = size, to = to)
        model.addAttribute("mails", entities)
        model.addAttribute("to", to)
        return "index"
    }

    @PostMapping("/clear-all")
    fun clear(): String {
        clearMailService.exec()
        return "redirect:/"
    }
}