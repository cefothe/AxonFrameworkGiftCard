package eu.stefanangelov.axon.giftcard.apicore

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class IssueCmd(@TargetAggregateIdentifier val id: String, val amount: Int)
data class IssuedEvt(val id: String, val amount: Int)
data class RedeemCmd(@TargetAggregateIdentifier val id: String, val amount: Int)
data class RedeemedEvt(val id: String, val amount: Int)
data class CancelCmd(@TargetAggregateIdentifier val id: String)
data class CancelEvt(val id: String)