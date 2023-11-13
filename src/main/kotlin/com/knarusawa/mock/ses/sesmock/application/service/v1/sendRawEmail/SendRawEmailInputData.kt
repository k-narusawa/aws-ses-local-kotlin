package com.knarusawa.mock.ses.sesmock.application.service.v1.sendRawEmail

data class SendRawEmailInputData(
        val version: String?,

        val configurationSetName: String?,

        val rawMessageData: String
) {
    companion object {
        fun of(
                version: String?,
                configurationSetName: String?,
                rawMessageData: String
        ) = SendRawEmailInputData(
                version = version,
                configurationSetName = configurationSetName,
                rawMessageData = rawMessageData
        )
    }
}