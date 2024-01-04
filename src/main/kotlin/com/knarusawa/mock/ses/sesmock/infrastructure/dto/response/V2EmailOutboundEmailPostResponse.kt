package com.knarusawa.mock.ses.sesmock.infrastructure.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class V2EmailOutboundEmailPostResponse(
        @JsonProperty("MessageId")
        val messageId: String
)