package com.knarusawa.mock.ses.sesmock.application.service.batchClearMail

import com.knarusawa.mock.ses.sesmock.infrastructure.repository.MailRepository
import com.knarusawa.mock.ses.sesmock.util.DateTimeUtil
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BatchClearMailService(
    private val mailRepository: MailRepository
) {
  fun exec(page: Int, size: Int, seconds: Int): Int {
    val pageEntity = mailRepository.findByAtBefore(
        createdAt = DateTimeUtil.secondsAgo(seconds.toLong()),
        pageable = PageRequest.of(page, size)
    )
    mailRepository.deleteAll(pageEntity)
    return pageEntity.numberOfElements
  }
}