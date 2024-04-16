package com.message.viewing.team.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle

@Entity
@Table(name = "teams", schema = "public")
class Team(
    name: String,
) {
    @Id
    @AggregateIdentifier
    val id: String = ""

    @Column
    var name: String = name
        protected set
}