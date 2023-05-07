package com.knarusawa.mock.ses.sesmock.repository

import com.knarusawa.mock.ses.sesmock.domain.MailEntity
import java.time.LocalDateTime
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MailRepository : CrudRepository<MailEntity, Long> {
//  fun findByFromAddress(fromAddress: String): List<MailEntity>
//
//  fun findByToAddress(toAddress: String): List<MailEntity>
//
//  fun findByFromAddressAndToAddress(fromAddress: String?, toAddress: String?): List<MailEntity>

  fun findByAtAfter(createdAt: LocalDateTime): List<MailEntity>
}