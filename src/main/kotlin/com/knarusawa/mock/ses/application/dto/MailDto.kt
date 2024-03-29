package com.knarusawa.mock.ses.application.dto

import com.knarusawa.mock.ses.domain.Mail
import com.knarusawa.mock.ses.util.StringUtil
import java.time.LocalDateTime

data class MailDto(
        val messageId: String,
        val from: String,
        val destination: Destination,
        val unsubscribeUrl: String? = null,
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
                unsubscribeUrl = mail.getUnsubscribeUrl(),
                subject = mail.subject,
                body = Body(
                        text = mail.textBody,
                        html = mail.htmlBody,
                ),
                at = mail.at
        )
    }
}