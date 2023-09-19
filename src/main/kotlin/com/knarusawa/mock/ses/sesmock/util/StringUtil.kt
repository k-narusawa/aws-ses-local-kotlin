package com.knarusawa.mock.ses.sesmock.util

import org.springframework.stereotype.Component

@Component
object StringUtil {
  fun convertTextToHtml(text: String): String {
    val urlRegex = """(https?://\S+)""".toRegex()
    return text
        .replace(urlRegex) { matchResult ->
          """<a href="${matchResult.value}" target="_blank">${matchResult.value}</a>"""
        }
        .replace("\n", "<br>")
  }
}