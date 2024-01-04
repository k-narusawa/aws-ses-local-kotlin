package com.knarusawa.mock.ses.application.service.clearMail

import com.knarusawa.mock.ses.adapter.gateway.db.MailRepository
import org.springframework.stereotype.Service

@Service
class ClearMailService(
        private val mailRepository: MailRepository
) {
    fun exec() {
        mailRepository.deleteAll()
    }
}