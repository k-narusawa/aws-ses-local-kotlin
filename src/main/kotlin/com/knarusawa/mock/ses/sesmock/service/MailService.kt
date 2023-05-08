package com.knarusawa.mock.ses.sesmock.service

import com.knarusawa.mock.ses.sesmock.domain.MailDto
import com.knarusawa.mock.ses.sesmock.domain.MailDtos
import com.knarusawa.mock.ses.sesmock.domain.SendMailRequestDto
import com.knarusawa.mock.ses.sesmock.repository.MailRepository
import com.knarusawa.mock.ses.sesmock.util.DateTimeUtil
import org.springframework.data.domain.PageRequest
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

  fun getEmails(since: String?): MailDtos {
    val entities = since?.let {
      mailRepository.findByAtAfter(
        createdAt = DateTimeUtil.timestampToLocalDateTime(it.toLong()),
        pageable = PageRequest.of(0, 10000)
      )
    } ?: mailRepository.findByAtAfter(
      createdAt = DateTimeUtil.minutesAgo(5L),
      pageable = PageRequest.of(0, 10000)
    )
    return MailDtos.from(entities)
  }

  fun getEmails(page: Int, size: Int, to: String?): MailDtos {
    val entities = to?.let{
      mailRepository.findByToOrderByAtDesc(
        toAddress = to,
        pageable = PageRequest.of(page, size)
      )
    } ?: mailRepository.findByOrderByAtDesc(pageable = PageRequest.of(page, size))
    return MailDtos.from(entities)
  }

  fun clearEmails() {
    mailRepository.deleteAll()
  }
}