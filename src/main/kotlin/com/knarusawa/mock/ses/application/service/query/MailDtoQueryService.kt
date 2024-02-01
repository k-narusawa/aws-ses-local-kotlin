package com.knarusawa.mock.ses.application.service.query

import com.knarusawa.mock.ses.adapter.gateway.db.MailRepository
import com.knarusawa.mock.ses.application.dto.MailListDto
import com.knarusawa.mock.ses.util.DateTimeUtil
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
@Transactional(readOnly = true)
class MailDtoQueryService(
        private val mailRepository: MailRepository
) {
    fun findByToAddress(toAddress: String?, page: Int, size: Int): MailListDto {
        if (toAddress.isNullOrEmpty()) {
            return MailListDto.from(mailRepository.findByOrderByAtDesc(pageable = PageRequest.of(page, size)))
        }
        return MailListDto.from(mailRepository.findByOrderByAtDesc(pageable = PageRequest.of(0, 1000)))
    }

    fun findByAtBetween(from: Long?, to: Long?): MailListDto {
        val mail = mailRepository.findByAtBetween(
                from = from?.let { DateTimeUtil.timestampToLocalDateTime(from) }
                        ?: LocalDateTime.MIN,
                to = to?.let { DateTimeUtil.timestampToLocalDateTime(to) } ?: LocalDateTime.MAX,
                pageable = PageRequest.of(0, 1000)
        )

        return MailListDto.from(mail)
    }
}