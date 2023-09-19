package com.knarusawa.mock.ses.sesmock.service

import com.knarusawa.mock.ses.sesmock.domain.MailListDto
import com.knarusawa.mock.ses.sesmock.domain.SendMailRequestDto
import com.knarusawa.mock.ses.sesmock.repository.MailRepository
import com.knarusawa.mock.ses.sesmock.util.DateTimeUtil
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MailService(
  private val mailRepository: MailRepository
) {
  fun sendEmail(sendMailRequestDto: SendMailRequestDto): String {
    val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"
    mailRepository.save(sendMailRequestDto.toMailEntity(messageId = messageId))
    return messageId
  }

  @Transactional(readOnly = true)
  fun getEmails(since: String?, to: String?): MailListDto {
    val entities = since?.let {
      mailRepository.findByToAndAtAfter(
        toAddress = to,
        createdAt = DateTimeUtil.timestampToLocalDateTime(it.toLong()),
        pageable = PageRequest.of(0, 1000)
      )
    } ?: mailRepository.findByToAndAtAfter(
      toAddress = to,
      createdAt = DateTimeUtil.minutesAgo(5L),
      pageable = PageRequest.of(0, 1000)
    )
    return MailListDto.from(entities)
  }

  @Transactional(readOnly = true)
  fun getEmails(page: Int, size: Int, to: String?): MailListDto {
    val entities = to?.let{
      mailRepository.findByToOrderByAtDesc(
        toAddress = to,
        pageable = PageRequest.of(page, size)
      )
    } ?: mailRepository.findByOrderByAtDesc(pageable = PageRequest.of(page, size))
    return MailListDto.from(entities)
  }

  fun clearEmails() {
    mailRepository.deleteAll()
  }

  fun batchClearEmails(page: Int, size: Int, seconds: Int): Int {
    val pageEntity = mailRepository.findByAtBefore(
      createdAt = DateTimeUtil.secondsAgo(seconds.toLong()),
      pageable = PageRequest.of(page, size)
    )
    mailRepository.deleteAll(pageEntity)
    return pageEntity.numberOfElements
  }
}