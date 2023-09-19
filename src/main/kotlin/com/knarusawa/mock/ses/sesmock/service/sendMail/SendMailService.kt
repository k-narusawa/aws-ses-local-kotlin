package com.knarusawa.mock.ses.sesmock.service.sendMail

import com.knarusawa.mock.ses.sesmock.domain.SendMailRequestDto
import com.knarusawa.mock.ses.sesmock.repository.MailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SendMailService(
  private val mailRepository: MailRepository
) {
  @Transactional
  fun exec(sendMailRequestDto: SendMailRequestDto): String {
    val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"
    mailRepository.save(sendMailRequestDto.toMailEntity(messageId = messageId))
    return messageId
  }
}