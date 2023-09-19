package com.knarusawa.mock.ses.sesmock.repository

import com.knarusawa.mock.ses.sesmock.domain.Mail
import java.time.LocalDateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MailRepository : CrudRepository<Mail, Long> {
  fun findByToOrderByAtDesc(
    toAddress: String?,
    pageable: Pageable
  ): Page<Mail>

  fun findByOrderByAtDesc(
    pageable: Pageable
  ): Page<Mail>

  fun findByAtBefore(createdAt: LocalDateTime, pageable: Pageable): Page<Mail>

  fun findByToAndAtAfter(toAddress: String?, createdAt: LocalDateTime, pageable: Pageable): Page<Mail>
}