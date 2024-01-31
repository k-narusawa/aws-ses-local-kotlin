package com.knarusawa.mock.ses.domain

import jakarta.persistence.*
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

        @Lob
        @Column(name = "text")
        val textBody: String?,

        @Lob
        @Column(name = "html")
        val htmlBody: String?,

        @Column(name = "list_unsubscribe_post")
        val listUnsubscribePost: String? = null,

        @Column(name = "list_unsubscribe_url")
        val listUnsubscribeUrl: String? = null,

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
                htmlBody: String?,
                listUnsubscribePost: String?,
                listUnsubscribeUrl: String?,
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
                    listUnsubscribePost = listUnsubscribePost,
                    listUnsubscribeUrl = listUnsubscribeUrl,
                    at = LocalDateTime.now()
            )
        }
    }

    fun getUnsubscribeUrl():String?{
        if(this.listUnsubscribePost != "List-Unsubscribe=One-Click") return null

        return this.listUnsubscribeUrl
    }
}
