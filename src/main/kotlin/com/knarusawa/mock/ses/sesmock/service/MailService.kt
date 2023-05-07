package com.knarusawa.mock.ses.sesmock.service

import com.knarusawa.mock.ses.sesmock.domain.MailDto
import com.knarusawa.mock.ses.sesmock.domain.SendMailRequestDto
import com.knarusawa.mock.ses.sesmock.repository.MailRepository
import org.springframework.stereotype.Service

@Service
class MailService(
  private val mailRepository: MailRepository
) {
  fun sendEmail(sendMailRequestDto: SendMailRequestDto): String {
    val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"
    mailRepository.save(sendMailRequestDto.toMailEntity(messageId = messageId))
    return messageId
  }

  fun getEmails(from: String?, to: String?, since: String?): List<MailDto> {
    val list = mailRepository.findAll()
    return list.map { MailDto.from(it) }
  }
}