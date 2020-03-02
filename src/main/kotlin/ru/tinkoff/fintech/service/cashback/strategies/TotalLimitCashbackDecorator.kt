package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.CashbackCalculator
import ru.tinkoff.fintech.service.cashback.MAX_CASH_BACK
import kotlin.math.abs

class TotalLimitCashbackDecorator(private val cc : CashbackCalculator) : CashbackCalculator by cc {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        val result = cc.calculateCashback(transactionInfo)
        return if(result + transactionInfo.cashbackTotalValue > MAX_CASH_BACK) abs(transactionInfo.cashbackTotalValue - MAX_CASH_BACK) else result
    }
}