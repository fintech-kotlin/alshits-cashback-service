package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.MAX_CASH_BACK

class LimitReachedCashbackStrategy(nextStrategy : CashbackStrategy = NoCashbackStrategy()) : AbstractCashbackStrategy(nextStrategy) {
    override fun isAppropriate(transactionInfo: TransactionInfo): Boolean = transactionInfo.cashbackTotalValue >= MAX_CASH_BACK
    override fun calculate(transactionInfo: TransactionInfo): Double = 0.0
}