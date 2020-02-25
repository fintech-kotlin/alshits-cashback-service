package ru.tinkoff.fintech.service.notification

import java.lang.StringBuilder
import java.util.stream.IntStream

class CardNumberMaskerImpl: CardNumberMasker {
    override fun mask(cardNumber: String, maskChar: Char, start: Int, end: Int): String {
        if(start > end) {
            throw Exception("Start index cannot be greater than end index")
        }

        val realEnd = if (cardNumber.length > end)  end  else cardNumber.length

        val sb = StringBuilder(cardNumber)
        IntStream.range(start, realEnd).forEach { i -> sb.setCharAt(i, maskChar)}
        return sb.toString()
    }
}