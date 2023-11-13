package com.knarusawa.mock.ses.sesmock.application.service.getMailList

import com.knarusawa.mock.ses.sesmock.application.dto.MailListDto
import com.knarusawa.mock.ses.sesmock.infrastructure.repository.MailRepository
import com.knarusawa.mock.ses.sesmock.util.DateTimeUtil
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class GetMailListService(
    private val mailRepository: MailRepository
) {
  @Transactional(readOnly = true)
  fun exec(since: String?, to: String?): MailListDto {
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
  fun exec(page: Int, size: Int, to: String?): MailListDto {
    val entities = to?.let {
      mailRepository.findByToOrderByAtDesc(
          toAddress = to,
          pageable = PageRequest.of(page, size)
      )
    } ?: mailRepository.findByOrderByAtDesc(pageable = PageRequest.of(page, size))
    return MailListDto.from(entities)
  }
}