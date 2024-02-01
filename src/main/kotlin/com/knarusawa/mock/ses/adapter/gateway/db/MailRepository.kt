package com.knarusawa.mock.ses.adapter.gateway.db

import com.knarusawa.mock.ses.domain.Mail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MailRepository : CrudRepository<Mail, Long> {
    fun findByToOrderByAtDesc(toAddress: String?, pageable: Pageable): Page<Mail>

    fun findByOrderByAtDesc(pageable: Pageable): Page<Mail>

    fun findByAtBefore(createdAt: LocalDateTime, pageable: Pageable): Page<Mail>

    fun findByAtBetween(from: LocalDateTime, to: LocalDateTime, pageable: Pageable): Page<Mail>
}