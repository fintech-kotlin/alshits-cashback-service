package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.strategies.*

internal const val LOYALTY_PROGRAM_BLACK = "BLACK"
internal const val LOYALTY_PROGRAM_ALL = "ALL"
internal const val LOYALTY_PROGRAM_BEER = "BEER"
internal const val MAX_CASH_BACK = 3000.0
internal const val MCC_SOFTWARE = 5734
internal const val MCC_BEER = 5921

class CashbackCalculatorImpl : CashbackCalculator {

    private val cashbackCalculator : CashbackCalculator

    init {
        val beerCashbackStrategy = BeerCashbackStrategy()
        val computerSoftwareCashbackStrategy = ComputerSoftwareCashbackStrategy(beerCashbackStrategy)
        val blackCashbackStrategy = BlackCashbackStrategy(computerSoftwareCashbackStrategy)
        val limitReachedCashbackStrategy = LimitReachedCashbackStrategy(blackCashbackStrategy)
        cashbackCalculator = TotalLimitCashbackDecorator(EvilSupplyDecorator(limitReachedCashbackStrategy))
    }

    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        return cashbackCalculator.calculateCashback(transactionInfo)
    }

}