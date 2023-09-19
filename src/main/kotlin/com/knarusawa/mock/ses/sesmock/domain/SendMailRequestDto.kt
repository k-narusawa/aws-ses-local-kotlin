package com.knarusawa.mock.ses.sesmock.domain

import java.time.LocalDateTime

data class SendMailRequestDto(
  val action: String,

  val version: String?,

  val configurationSetName: String?,

  val toAddress: String?,

  val ccAddress: String?,

  val bccAddress: String?,

  val htmlData: String?,

  val htmlCharset: String?,

  val textData: String?,

  val textCharset: String?,

  val subjectData: String,

  val subjectCharset: String?,

  val replyToAddress: String?,

  val returnPath: String?,

  val returnPathArn: String?,

  val source: String,

  val sourceArn: String?,

  val tags: String?
) {
  companion object {
    fun of(
      action: String,
      version: String?,
      configurationSetName: String?,
      toAddress: String?,
      ccAddress: String?,
      bccAddress: String?,
      htmlData: String?,
      htmlCharset: String?,
      textData: String?,
      textCharset: String?,
      subjectData: String,
      subjectCharset: String?,
      replyToAddress: String?,
      returnPath: String?,
      returnPathArn: String?,
      source: String,
      sourceArn: String?,
      tags: String?,
    ) = SendMailRequestDto(
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
  }

  fun toMailEntity(messageId: String) = Mail(
    messageId = messageId,
    from = source,
    to = toAddress,
    cc = ccAddress,
    bcc = bccAddress,
    subject = subjectData,
    textBody = textData,
    htmlBody = htmlData,
    at = LocalDateTime.now()
  )
}