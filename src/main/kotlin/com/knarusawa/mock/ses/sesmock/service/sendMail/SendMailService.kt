package com.knarusawa.mock.ses.sesmock.service.sendMail

import com.knarusawa.mock.ses.sesmock.domain.Mail
import com.knarusawa.mock.ses.sesmock.repository.MailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SendMailService(
  private val mailRepository: MailRepository
) {
  @Transactional
  fun exec(sendMailInputData: SendMailInputData): String {
    val messageId = "ses-${(Math.random() * 900000000 + 100000000).toInt()}"

    val mail = Mail.of(
      messageId = messageId,
      source = sendMailInputData.source,
      toAddress = sendMailInputData.toAddress,
      ccAddress = sendMailInputData.ccAddress,
      bccAddress = sendMailInputData.bccAddress,
      subjectData = sendMailInputData.subjectData,
      textData = sendMailInputData.textData,
      htmlData = sendMailInputData.htmlData,
    )

    mailRepository.save(mail)

    return messageId
  }
}