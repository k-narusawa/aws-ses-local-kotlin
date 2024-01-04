package com.knarusawa.mock.ses.application.service.v2

import com.knarusawa.mock.ses.adapter.dto.request.V2EmailOutboundEmailPostRequest

data class SesV2ApiInputData(
        val configurationSetName: String?,
        val content: Content,
        val destination: Destination,
        val emailTags: List<EmailTag>?,
        val feedbackForwardingEmailAddress: String?,
        val feedbackForwardingEmailAddressIdentityArn: String?,
        val fromEmailAddress: String?,
        val fromEmailAddressIdentityArn: String?,
        val listManagementOptions: ListManagementOptions?,
        val replyToAddresses: List<String>?
) {
    data class Content(
            val raw: Raw?,
            val simple: Simple?,
            val template: Template?
    ) {
        data class Raw(
                val data: String,
        )

        data class Simple(
                val body: Body,
                val subject: Subject
        ) {
            data class Body(
                    val html: Html?,
                    val text: Text?
            ) {
                data class Html(
                        val charSet: String?,
                        val data: String
                )

                data class Text(
                        val charSet: String?,
                        val data: String
                )
            }

            data class Subject(
                    val charSet: String?,
                    val data: String
            )
        }

        data class Template(
                val templateArn: String?,
                val templateData: String?,
                val templateName: String?
        )
    }

    data class Destination(
            val bccAddress: String?,
            val ccAddress: String?,
            val toAddress: String?
    )

    data class EmailTag(
            val name: String,
            val value: String
    )

    data class ListManagementOptions(
            val contactListName: String?,
            val topicName: String?
    )

    companion object {
        fun fromV2EmailOutboundEmailPostRequest(request: V2EmailOutboundEmailPostRequest): SesV2ApiInputData {
            return SesV2ApiInputData(
                    configurationSetName = request.configurationSetName,
                    content = Content(
                            raw = request.content.raw?.let { Content.Raw(data = it.data) },
                            simple = request.content.simple?.let {
                                Content.Simple(
                                        body = Content.Simple.Body(
                                                html = it.body.html?.let { html -> Content.Simple.Body.Html(charSet = html.charSet, data = html.data) },
                                                text = it.body.text?.let { text -> Content.Simple.Body.Text(charSet = text.charSet, data = text.data) }
                                        ),
                                        subject = Content.Simple.Subject(charSet = it.subject.charSet, data = it.subject.data)
                                )
                            },
                            template = request.content.template?.let {
                                Content.Template(
                                        templateArn = it.templateArn,
                                        templateData = it.templateData,
                                        templateName = it.templateName
                                )
                            }
                    ),
                    destination = Destination(
                            bccAddress = request.destination.bccAddress,
                            ccAddress = request.destination.ccAddress,
                            toAddress = request.destination.toAddress
                    ),
                    emailTags = request.emailTags?.map { EmailTag(name = it.name, value = it.value) },
                    feedbackForwardingEmailAddress = request.feedbackForwardingEmailAddress,
                    feedbackForwardingEmailAddressIdentityArn = request.feedbackForwardingEmailAddressIdentityArn,
                    fromEmailAddress = request.fromEmailAddress,
                    fromEmailAddressIdentityArn = request.fromEmailAddressIdentityArn,
                    listManagementOptions = request.listManagementOptions?.let {
                        ListManagementOptions(
                                contactListName = it.contactListName,
                                topicName = it.topicName
                        )
                    },
                    replyToAddresses = request.replyToAddresses
            )
        }
    }
}
