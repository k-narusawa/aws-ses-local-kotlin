package com.knarusawa.mock.ses.sesmock.controller

import com.knarusawa.mock.ses.sesmock.domain.MailDtos
import com.knarusawa.mock.ses.sesmock.domain.SendMailRequestDto
import com.knarusawa.mock.ses.sesmock.service.MailService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SESRestController(
  private val mailService: MailService
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
    @RequestParam(name = "Message.Subject.Data") subjectData: String,
    @RequestParam(name = "Message.Subject.Charset") subjectCharset: String?,
    @RequestParam(name = "ReplyToAddresses.member.1") replyToAddress: String?,
    @RequestParam(name = "ReturnPath") returnPath: String?,
    @RequestParam(name = "ReturnPathArn") returnPathArn: String?,
    @RequestParam(name = "Source") source: String,
    @RequestParam(name = "SourceArn") sourceArn: String?,
    @RequestParam(name = "Tags.member.1") tags: String?,
  ): String {
    val sendMailRequestDto = SendMailRequestDto.of(
      action = action,
      version = version,
      configurationSetName = configurationSetName,
      toAddress = toAddress,
      ccAddress = ccAddress,
      bccAddress = bccAddress,
      htmlData = htmlData,
      htmlCharset = htmlCharset,
      textData = textData,
      textCharset = textCharset,
      subjectData = subjectData,
      subjectCharset = subjectCharset,
      replyToAddress = replyToAddress,
      returnPath = returnPath,
      returnPathArn = returnPathArn,
      source = source,
      sourceArn = sourceArn,
      tags = tags
    )
    val messageId = mailService.sendEmail(
      sendMailRequestDto = sendMailRequestDto
    )
    return """
      <?xml version="1.0" encoding="UTF-8"?><SendEmailResponse xmlns="http://ses.amazonaws.com/doc/2010-12-01/"><SendEmailResult><MessageId>${messageId}</MessageId></SendEmailResult></SendEmailResponse>
    """.trimIndent()
  }

  @GetMapping("/store")
  @ResponseStatus(HttpStatus.OK)
  fun store(
    @RequestParam since: String?,
  ): MailDtos {
    return mailService.getEmails(since = since)
  }

  @GetMapping("/emails")
  @ResponseStatus(HttpStatus.OK)
  fun getEmails(
    @RequestParam to: String?,
    @RequestParam(defaultValue = "0") page: Int,
    @RequestParam(defaultValue = "10") size: Int,
  ): MailDtos {
    return mailService.getEmails(
      page = page,
      size = size,
      to = to,
    )
  }

  @PostMapping("/clear")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun clearEmails() {
    mailService.clearEmails()
  }
}