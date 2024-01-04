package com.knarusawa.mock.ses.sesmock.application.service.v1.sendRawEmail

import com.knarusawa.mock.ses.sesmock.adapter.gateway.db.MailRepository
import com.knarusawa.mock.ses.sesmock.domain.Mail
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.MimeMessage

@Service
class SendRawEmailSendService(
        private val mailRepository: MailRepository
) {
    fun exec(inputData: SendRawEmailInputData): String {
        val decodedData = Base64.getDecoder().decode(inputData.rawMessageData).toString(Charsets.UTF_8)
        val message = parseRawEmail(decodedData)
        val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"

        val mail = Mail.of(
                messageId = messageId,
                from = message.from.joinToString(),
                to = message.getRecipients(Message.RecipientType.TO).joinToString(),
                cc = "",
                bcc = "",
                subject = message.subject,
                textBody = message.content.toString(),
                htmlBody = "",
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