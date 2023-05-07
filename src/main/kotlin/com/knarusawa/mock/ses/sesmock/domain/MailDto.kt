package com.knarusawa.mock.ses.sesmock.domain

import java.time.LocalDateTime

data class MailDto(
  val messageId: String,
  val from: String,
  val destination: Destination,
  val subject: String,
  val body: Body,
  val at: LocalDateTime
) {

  data class Destination(
    val to: List<String?>,
    val cc: List<String?>,
    val bcc: List<String?>
  ) {
    companion object {
      fun of(to: String?, cc: String?, bcc: String?) = Destination(
        to = listOf(to),
        cc = listOf(cc),
        bcc = listOf(bcc)
      )
    }
  }

  data class Body(
    val text: String?,
    val html: String?,
  ) {
    companion object {
      fun of(text: String?, html: String?) = Body(
        text = text,
        html = html
      )
    }
  }

  companion object {
    fun from(mailEntity: MailEntity) = MailDto(
      messageId = mailEntity.messageId,
      from = mailEntity.from,
      destination = Destination.of(
        to = mailEntity.to,
        cc = mailEntity.cc,
        bcc = mailEntity.bcc,
      ),
      subject = mailEntity.subject,
      body = Body.of(
        text = mailEntity.textBody,
        html = mailEntity.htmlBody,
      ),
      at = mailEntity.at
    )
  }
}