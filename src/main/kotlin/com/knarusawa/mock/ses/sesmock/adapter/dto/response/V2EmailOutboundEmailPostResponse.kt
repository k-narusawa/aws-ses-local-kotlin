package com.knarusawa.mock.ses.sesmock.adapter.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class V2EmailOutboundEmailPostResponse(
        @JsonProperty("MessageId")
        val messageId: String
)