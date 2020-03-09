package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo

class NoCashbackStrategy : CashbackStrategy {
    override fun isAppropriate(transactionInfo: TransactionInfo): Boolean = true
    override fun calculate(transactionInfo: TransactionInfo): Double = 0.0
    override fun calculateCashback(transactionInfo: TransactionInfo): Double = 0.0
}