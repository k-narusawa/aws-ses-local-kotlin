package com.knarusawa.mock.ses.sesmock.application.service.dto

import com.knarusawa.mock.ses.sesmock.domain.Mail
import com.knarusawa.mock.ses.sesmock.util.StringUtil
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
  )

  data class Body(
      val text: String?,
      val viewText: String? = StringUtil.convertTextToHtml(text ?: ""),
      val html: String?,
  )

  companion object {
    fun from(mail: Mail) = MailDto(
        messageId = mail.messageId,
        from = mail.from,
        destination = Destination(
            to = listOf(mail.to),
            cc = listOf(mail.cc),
            bcc = listOf(mail.bcc),
        ),
        subject = mail.subject,
        body = Body(
            text = mail.textBody,
            html = mail.htmlBody,
        ),
        at = mail.at
    )
  }
}