package com.knarusawa.mock.ses.sesmock.service.clearMail

import com.knarusawa.mock.ses.sesmock.repository.MailRepository
import com.knarusawa.mock.ses.sesmock.service.sendMail.SendMailService
import org.springframework.stereotype.Service

@Service
class ClearMailService(
  private val mailRepository: MailRepository
) {
  fun exec() {
    mailRepository.deleteAll()
  }
}