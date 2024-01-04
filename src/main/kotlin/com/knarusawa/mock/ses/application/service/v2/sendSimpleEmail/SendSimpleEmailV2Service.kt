package com.knarusawa.mock.ses.application.service.v2.sendSimpleEmail

import com.knarusawa.mock.ses.adapter.gateway.db.MailRepository
import com.knarusawa.mock.ses.application.service.v2.SesV2ApiInputData
import com.knarusawa.mock.ses.domain.Mail
import org.springframework.stereotype.Service

@Service
class SendSimpleEmailV2Service(
        private val mailRepository: MailRepository
) {
    fun exec(inputData: SesV2ApiInputData): String {
        val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"

        val mail = Mail.of(
                messageId = messageId,
                from = inputData.fromEmailAddress ?: "",
                to = inputData.destination.toAddresses,
                cc = inputData.destination.ccAddresses,
                bcc = inputData.destination.bccAddresses,
                subject = inputData.content.simple?.subject?.data ?: "",
                textBody = inputData.content.simple?.body?.text?.data ?: "",
                htmlBody = inputData.content.simple?.body?.html?.data ?: "",
        )

        mailRepository.save(mail)

        return messageId
    }
}