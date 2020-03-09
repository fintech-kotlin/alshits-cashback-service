package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo

abstract class AbstractCashbackStrategy(private var nextStrategy : CashbackStrategy) : CashbackStrategy {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double =
        if(isAppropriate(transactionInfo)) calculate(transactionInfo) else nextStrategy.calculateCashback(transactionInfo)
}