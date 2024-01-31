package com.knarusawa.mock.ses.application.service.v2.sendRawEmail

import com.knarusawa.mock.ses.adapter.gateway.db.MailRepository
import com.knarusawa.mock.ses.application.service.v2.SesV2ApiInputData
import com.knarusawa.mock.ses.domain.Mail
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.MimeMessage

@Service
class SendRawEmailV2Service(
        private val mailRepository: MailRepository
) {
    fun exec(inputData: SesV2ApiInputData): String {
        val decodedData = Base64.getDecoder()
                .decode(inputData.content.raw?.data)
                .toString(Charsets.UTF_8)

        val message = parseRawEmail(decodedData)

        val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"

        val mail = Mail.of(
                messageId = messageId,
                from = inputData.fromEmailAddress ?: "",
                to = message.getRecipients(Message.RecipientType.TO).joinToString(),
                cc = "",
                bcc = "",
                subject = message.subject,
                textBody = message.content.toString(),
                htmlBody = "",
                listUnsubscribePost = message.getHeader("List-Unsubscribe-Post").firstOrNull() ?: "",
                listUnsubscribeUrl = message.getHeader("List-Unsubscribe").firstOrNull() ?: "",
        )

        mailRepository.save(mail)

        return messageId
    }

    private fun parseRawEmail(rawEmail: String): MimeMessage {
        val session = Session.getDefaultInstance(System.getProperties())
        val inputStream = ByteArrayInputStream(rawEmail.toByteArray(StandardCharsets.UTF_8))
        return MimeMessage(session, inputStream)
    }
}