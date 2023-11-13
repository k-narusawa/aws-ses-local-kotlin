package com.knarusawa.mock.ses.sesmock.infrastructure.controller

import com.knarusawa.mock.ses.sesmock.application.dto.MailListDto
import com.knarusawa.mock.ses.sesmock.application.service.batchClearMail.BatchClearMailService
import com.knarusawa.mock.ses.sesmock.application.service.clearMail.ClearMailService
import com.knarusawa.mock.ses.sesmock.application.service.getMailList.GetMailListService
import com.knarusawa.mock.ses.sesmock.application.service.v1.sendEmail.SendEmailInputData
import com.knarusawa.mock.ses.sesmock.application.service.v1.sendEmail.SendEmailService
import com.knarusawa.mock.ses.sesmock.application.service.v1.sendRawEmail.SendRawEmailInputData
import com.knarusawa.mock.ses.sesmock.application.service.v1.sendRawEmail.SendRawEmailSendService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SESRestController(
        private val sendEmailService: SendEmailService,
        private val sendRawEmailSendService: SendRawEmailSendService,
        private val getMailService: GetMailListService,
        private val clearMailService: ClearMailService,
        private val batchClearMailService: BatchClearMailService
) {
  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  fun sendMail(
    @RequestParam(name = "Action") action: String,
    @RequestParam(name = "Version") version: String?,
    @RequestParam(name = "ConfigurationSetName") configurationSetName: String?,
    @RequestParam(name = "Destination.ToAddresses.member.1") toAddress: String?,
    @RequestParam(name = "Destination.CcAddresses.member.1") ccAddress: String?,
    @RequestParam(name = "Destination.BccAddresses.member.1") bccAddress: String?,
    @RequestParam(name = "Message.Body.Html.Data") htmlData: String?,
    @RequestParam(name = "Message.Body.Html.Charset") htmlCharset: String?,
    @RequestParam(name = "Message.Body.Text.Data") textData: String?,
    @RequestParam(name = "Message.Body.Text.Charset") textCharset: String?,
    @RequestParam(name = "Message.Subject.Data") subjectData: String?,
    @RequestParam(name = "Message.Subject.Charset") subjectCharset: String?,
    @RequestParam(name = "ReplyToAddresses.member.1") replyToAddress: String?,
    @RequestParam(name = "ReturnPath") returnPath: String?,
    @RequestParam(name = "ReturnPathArn") returnPathArn: String?,
    @RequestParam(name = "Source") source: String?,
    @RequestParam(name = "SourceArn") sourceArn: String?,
    @RequestParam(name = "Tags.member.1") tags: String?,
    // SendRawEmail
    @RequestParam(name = "Destination.member.1") member: String?,
    @RequestParam(name = "FromArn") fromArn: String?,
    @RequestParam(name = "RawMessage.Data") rawMessageData: String?,
  ): String {
    if(action == "SendEmail"){
      val sendEmailInputData = SendEmailInputData.of(
              version = version,
              configurationSetName = configurationSetName,
              toAddress = toAddress,
              ccAddress = ccAddress,
              bccAddress = bccAddress,
              htmlData = htmlData,
              htmlCharset = htmlCharset,
              textData = textData,
              textCharset = textCharset,
              subjectData = subjectData!!, // SendEmailの場合は必ず存在する
              subjectCharset = subjectCharset,
              replyToAddress = replyToAddress,
              returnPath = returnPath,
              returnPathArn = returnPathArn,
              source = source!!,  // SendEmailの場合は必ず存在する
              sourceArn = sourceArn,
              tags = tags
      )
      val messageId = sendEmailService.exec(
              inputData = sendEmailInputData
      )
      return """
      <?xml version="1.0" encoding="UTF-8"?><SendEmailResponse xmlns="http://ses.amazonaws.com/doc/2010-12-01/"><SendEmailResult><MessageId>${messageId}</MessageId></SendEmailResult></SendEmailResponse>
    """.trimIndent()
    }

    if(action == "SendRawEmail"){
      val inputData = SendRawEmailInputData.of(
              version = version,
              configurationSetName = configurationSetName,
              rawMessageData = rawMessageData!! // SendRawEmailの場合は必ず存在する
      )

      val messageId = sendRawEmailSendService.exec(
              inputData = inputData
      )

      return """
        <?xml version="1.0" encoding="UTF-8"?><SendRawEmailResponse xmlns="http://ses.amazonaws.com/doc/2010-12-01/"><SendRawEmailResult><MessageId>${messageId}</MessageId></SendRawEmailResult></SendRawEmailResponse>
      """.trimIndent()
    }

    throw RuntimeException("Not implemented")
  }

  @GetMapping("/store")
  @ResponseStatus(HttpStatus.OK)
  fun store(
    @RequestParam since: String?,
    @RequestParam to: String?
  ): MailListDto {
    return getMailService.exec(since = since, to = to)
  }

  @GetMapping("/emails")
  @ResponseStatus(HttpStatus.OK)
  fun getEmails(
    @RequestParam to: String?,
    @RequestParam(defaultValue = "0") page: Int,
    @RequestParam(defaultValue = "10") size: Int,
  ): MailListDto {
    return getMailService.exec(
      page = page,
      size = size,
      to = to,
    )
  }

  @PostMapping("/clear")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun clearEmails() {
    clearMailService.exec()
  }

  @DeleteMapping("/batch-clear")
  @ResponseStatus(HttpStatus.OK)
  fun batchClearEmails(
    @RequestParam(defaultValue = "0") page: Int,
    @RequestParam(defaultValue = "0") size: Int,
    @RequestParam(defaultValue = "300") seconds: Int
  ): Int {
    return batchClearMailService.exec(
      page = page,
      size = size,
      seconds = seconds
    )
  }
}