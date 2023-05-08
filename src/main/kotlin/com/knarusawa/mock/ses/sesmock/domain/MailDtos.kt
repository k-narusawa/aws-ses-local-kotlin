package com.knarusawa.mock.ses.sesmock.domain

import org.springframework.data.domain.Page

data class MailDtos(
  val emails: List<MailDto>,
  val currentPage: Int,
  val totalPage: Int,
  val totalElements: Long,
){
  companion object {
    fun from(emails: Page<MailEntity>) = MailDtos(
      emails = emails.toList().map { MailDto.from(it) },
      currentPage = emails.pageable.pageNumber,
      totalPage = emails.totalPages,
      totalElements = emails.totalElements
    )
  }
}
