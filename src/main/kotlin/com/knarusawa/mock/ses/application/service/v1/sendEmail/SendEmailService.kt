package com.knarusawa.mock.ses.application.service.v1.sendEmail

import com.knarusawa.mock.ses.adapter.gateway.db.MailRepository
import com.knarusawa.mock.ses.domain.Mail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SendEmailService(
        private val mailRepository: MailRepository
) {
    @Transactional
    fun exec(inputData: SendEmailInputData): String {
        val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"

        val mail = Mail.of(
                messageId = messageId,
                from = inputData.source,
                to = inputData.toAddress,
                cc = inputData.ccAddress,
                bcc = inputData.bccAddress,
                subject = inputData.subjectData,
                textBody = inputData.textData,
                htmlBody = inputData.htmlData,
                listUnsubscribePost = "",
                listUnsubscribeUrl = "",
        )

        mailRepository.save(mail)

        return messageId
    }
}