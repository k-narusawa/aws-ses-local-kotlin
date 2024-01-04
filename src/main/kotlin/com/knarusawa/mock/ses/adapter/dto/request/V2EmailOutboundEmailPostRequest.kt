package com.knarusawa.mock.ses.adapter.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class V2EmailOutboundEmailPostRequest(
        @JsonProperty("ConfigurationSetName")
        val configurationSetName: String?,

        @JsonProperty("Content")
        val content: Content,

        @JsonProperty("Destination")
        val destination: Destination,

        @JsonProperty("EmailTags")
        val emailTags: List<EmailTag>?,

        @JsonProperty("FeedbackForwardingEmailAddress")
        val feedbackForwardingEmailAddress: String?,

        @JsonProperty("FeedbackForwardingEmailAddressIdentityArn")
        val feedbackForwardingEmailAddressIdentityArn: String?,

        @JsonProperty("FromEmailAddress")
        val fromEmailAddress: String?,

        @JsonProperty("FromEmailAddressIdentityArn")
        val fromEmailAddressIdentityArn: String?,

        @JsonProperty("ListManagementOptions")
        val listManagementOptions: ListManagementOptions?,

        @JsonProperty("ReplyToAddresses")
        val replyToAddresses: List<String>?
) {
    data class Content(
            @JsonProperty("Raw")
            val raw: Raw?,

            @JsonProperty("Simple")
            val simple: Simple?,

            @JsonProperty("Template")
            val template: Template?
    ) {
        data class Raw(
                @JsonProperty("Data")
                val data: String,
        )

        data class Simple(
                @JsonProperty("Body")
                val body: Body,
                @JsonProperty("Subject")
                val subject: Subject
        ) {
            data class Body(
                    @JsonProperty("Html")
                    val html: Html?,

                    @JsonProperty("Text")
                    val text: Text?
            ) {
                data class Html(
                        @JsonProperty("CharSet")
                        val charSet: String?,
                        @JsonProperty("Data")
                        val data: String
                )

                data class Text(
                        @JsonProperty("CharSet")
                        val charSet: String?,
                        @JsonProperty("Data")
                        val data: String
                )
            }

            data class Subject(
                    @JsonProperty("CharSet")
                    val charSet: String?,
                    @JsonProperty("Data")
                    val data: String
            )
        }

        data class Template(
                @JsonProperty("TemplateArn")
                val templateArn: String?,
                @JsonProperty("TemplateData")
                val templateData: String?,
                @JsonProperty("TemplateName")
                val templateName: String?
        )
    }

    data class Destination(
            @JsonProperty("BccAddress")
            val bccAddress: String?,
            @JsonProperty("CcAddress")
            val ccAddress: String?,
            @JsonProperty("ToAddress")
            val toAddress: String?
    )

    data class EmailTag(
            @JsonProperty("Name")
            val name: String,
            @JsonProperty("Value")
            val value: String
    )

    data class ListManagementOptions(
            @JsonProperty("ContactListName")
            val contactListName: String?,
            @JsonProperty("TopicName")
            val topicName: String?
    )
}
