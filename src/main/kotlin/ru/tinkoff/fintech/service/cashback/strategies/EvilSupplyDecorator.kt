package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.CashbackCalculator
import kotlin.math.abs

fun Double.equalsDelta(other: Double) = abs(this - other) < 0.000001

class EvilSupplyDecorator(private val cc : CashbackCalculator) : CashbackCalculator by cc {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        val result = cc.calculateCashback(transactionInfo)
        return if(0.0.equalsDelta(transactionInfo.transactionSum.rem(666))) result + 6.66 else result
    }
}