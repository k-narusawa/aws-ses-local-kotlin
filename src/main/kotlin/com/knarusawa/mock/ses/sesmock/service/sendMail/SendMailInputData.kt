package com.knarusawa.mock.ses.sesmock.service.sendMail

import com.knarusawa.mock.ses.sesmock.domain.Mail
import java.time.LocalDateTime

data class SendMailInputData(
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
    ) = SendMailInputData(
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
}