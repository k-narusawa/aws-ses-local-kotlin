package com.knarusawa.mock.ses.sesmock.repository

import com.knarusawa.mock.ses.sesmock.domain.MailEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MailRepository: CrudRepository<MailEntity, Long>