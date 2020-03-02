package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_ALL
import ru.tinkoff.fintech.service.cashback.MCC_SOFTWARE
import java.math.BigDecimal

class ComputerSoftwareCashbackStrategy(nextStrategy : CashbackStrategy = NoCashbackStrategy()) : AbstractCashbackStrategy(nextStrategy) {
    override fun isAppropriate(transactionInfo: TransactionInfo): Boolean =
        transactionInfo.mccCode == MCC_SOFTWARE &&
                LOYALTY_PROGRAM_ALL == transactionInfo.loyaltyProgramName &&
                isPolyndrom(transactionInfo.transactionSum)

    override fun calculate(transactionInfo: TransactionInfo): Double =
        transactionInfo.transactionSum * (nok(transactionInfo.firstName.length, transactionInfo.lastName.length).toDouble() / 1000.0) / 100.0

    private fun isPolyndrom(d: Double) : Boolean {
        val charArray = d.toBigDecimal().multiply(BigDecimal(100)).toPlainString().split(".")[0].toCharArray()

        val polyndromChecksNumber = charArray.size / 2
        var noErrors = true
        var i = 0
        while(i < polyndromChecksNumber) {
            if(charArray[i] != charArray[charArray.size -1 -i]) {
                if(noErrors) {
                    noErrors = false
                } else {
                    return false
                }
            }
            i++
        }
        return true
    }

    // Наименьшее общее кратное
    private fun nok(n1: Int, n2: Int): Int {
        return n1 * n2 / nod(n1, n2)
    }

    // Наибольший общий делитель
    private fun nod(n1: Int, n2: Int): Int {
        val div: Int
        if (n1 == n2) return n1
        var d = n1 - n2
        if (d < 0) {
            d = -d
            div = nod(n1, d)
        } else div = nod(n2, d)
        return div
    }
}