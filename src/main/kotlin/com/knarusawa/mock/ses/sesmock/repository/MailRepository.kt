package com.knarusawa.mock.ses.sesmock.repository

import com.knarusawa.mock.ses.sesmock.domain.MailEntity
import java.time.LocalDateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MailRepository : CrudRepository<MailEntity, Long> {
  fun findByToOrderByAtDesc(
    toAddress: String?,
    pageable: Pageable
  ): Page<MailEntity>

  fun findByOrderByAtDesc(
    pageable: Pageable
  ): Page<MailEntity>

  fun findByAtAfter(createdAt: LocalDateTime, pageable: Pageable): Page<MailEntity>
}