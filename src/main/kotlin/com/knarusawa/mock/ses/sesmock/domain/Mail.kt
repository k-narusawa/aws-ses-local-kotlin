package com.knarusawa.mock.ses.sesmock.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "ses_mock")
class Mail private constructor(
        @Id
        @Column(name = "message_id")
        val messageId: String,

        @Column(name = "from_address")
        val from: String,

        @Column(name = "to_address")
        val to: String?,

        @Column(name = "cc_address")
        val cc: String?,

        @Column(name = "bcc_address")
        val bcc: String?,

        @Column(name = "subject")
        val subject: String,

        @Column(name = "text")
        val textBody: String?,

        @Column(name = "html")
        val htmlBody: String?,

        @Column(name = "created_at")
        val at: LocalDateTime
) {
    companion object {
        fun of(
                messageId: String,
                from: String,
                to: String?,
                cc: String?,
                bcc: String?,
                subject: String,
                textBody: String?,
                htmlBody: String?
        ): Mail {
            return Mail(
                    messageId = messageId,
                    from = from,
                    to = to,
                    cc = cc,
                    bcc = bcc,
                    subject = subject,
                    textBody = textBody,
                    htmlBody = htmlBody,
                    at = LocalDateTime.now()
            )
        }
    }
}
