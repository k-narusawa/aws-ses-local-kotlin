package com.knarusawa.mock.ses.sesmock.application.service.v1.sendEmail

import com.knarusawa.mock.ses.sesmock.domain.Mail
import com.knarusawa.mock.ses.sesmock.infrastructure.repository.MailRepository
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
    )

    mailRepository.save(mail)

    return messageId
  }
}