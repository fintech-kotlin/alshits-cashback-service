package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.CashbackCalculator

interface CashbackStrategy : CashbackCalculator {
    fun isAppropriate(transactionInfo: TransactionInfo) : Boolean
    fun calculate(transactionInfo: TransactionInfo): Double
}