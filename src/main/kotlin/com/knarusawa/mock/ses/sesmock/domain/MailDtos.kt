package com.knarusawa.mock.ses.sesmock.domain

import org.springframework.data.domain.Page

data class MailDtos(
  val emails: List<MailDto>,
  val totalPage: Int,
  val totalElements: Long,
){
  companion object {
    fun from(emails: Page<MailEntity>) = MailDtos(
      emails = emails.toList().map { MailDto.from(it) },
      totalPage = emails.totalPages,
      totalElements = emails.totalElements
    )
  }
}
