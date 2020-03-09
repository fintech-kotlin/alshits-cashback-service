package ru.tinkoff.fintech.service.cashback.strategies

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_BEER
import ru.tinkoff.fintech.service.cashback.MCC_BEER
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

private val RU_LOCALE = Locale("ru", "RU")

class BeerCashbackStrategy(nextStrategy : CashbackStrategy = NoCashbackStrategy()) : AbstractCashbackStrategy(nextStrategy) {
    override fun isAppropriate(transactionInfo: TransactionInfo): Boolean =
        transactionInfo.mccCode == MCC_BEER && LOYALTY_PROGRAM_BEER == transactionInfo.loyaltyProgramName

    override fun calculate(transactionInfo: TransactionInfo): Double {
        return transactionInfo.transactionSum * when {
            isOlegOlegov(transactionInfo) -> 0.1
            isOleg(transactionInfo) -> 0.07
            isCurrentMonthFirstLetterMatch(transactionInfo) -> 0.05
            isNearMonthFirstLetterMatch(transactionInfo) -> 0.03
            else -> 0.02
        }
    }

    private fun isOlegOlegov(ti: TransactionInfo) =
        "Олег".equals(ti.firstName.toLowerCase(), true) && "Олегов".equals(ti.lastName.toLowerCase(), true)

    private fun isOleg(ti: TransactionInfo) =
        "Олег".equals(ti.firstName, true)

    private fun isCurrentMonthFirstLetterMatch(ti: TransactionInfo) =
        ti.firstName[0].equals(LocalDate.now().month.getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru","RU"))[0], true)

    private fun isNearMonthFirstLetterMatch(ti: TransactionInfo) : Boolean {
        val firstLetter = ti.firstName[0]
        val nextMonthLetter = LocalDate.now().plusMonths(1).month.getDisplayName(TextStyle.FULL_STANDALONE, RU_LOCALE)[0]
        val prevMonthLetter = LocalDate.now().minusMonths(1).month.getDisplayName(TextStyle.FULL_STANDALONE, RU_LOCALE)[0]
        return firstLetter.equals(nextMonthLetter,true) || firstLetter.equals(prevMonthLetter,true)
    }
}