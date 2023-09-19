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
    fun from(mail: Mail) = MailDto(
      messageId = mail.messageId,
      from = mail.from,
      destination = Destination.of(
        to = mail.to,
        cc = mail.cc,
        bcc = mail.bcc,
      ),
      subject = mail.subject,
      body = Body.of(
        text = mail.textBody,
        html = mail.htmlBody,
      ),
      at = mail.at
    )
  }
}