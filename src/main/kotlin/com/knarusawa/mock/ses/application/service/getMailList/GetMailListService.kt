package com.knarusawa.mock.ses.application.service.getMailList

import com.knarusawa.mock.ses.adapter.gateway.db.MailRepository
import com.knarusawa.mock.ses.application.dto.MailListDto
import com.knarusawa.mock.ses.util.DateTimeUtil
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class GetMailListService(
        private val mailRepository: MailRepository
) {
    @Transactional(readOnly = true)
    fun exec(since: String?, to: String?): MailListDto {
        val entities = to?.let {
            mailRepository.findByToOrderByAtDesc(
                    toAddress = to,
                    pageable = PageRequest.of(0, 1000)
            )
        } ?: mailRepository.findByOrderByAtDesc(pageable = PageRequest.of(0, 1000))
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