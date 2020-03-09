package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_BLACK

class BlackCashbackStrategy(nextStrategy : CashbackStrategy = NoCashbackStrategy()) : AbstractCashbackStrategy(nextStrategy) {
    override fun isAppropriate(transactionInfo: TransactionInfo): Boolean = LOYALTY_PROGRAM_BLACK == transactionInfo.loyaltyProgramName
    override fun calculate(transactionInfo: TransactionInfo): Double = transactionInfo.transactionSum * 0.01
}