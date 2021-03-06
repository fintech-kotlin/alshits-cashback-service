package ru.tinkoff.fintech.service.notification

import ru.tinkoff.fintech.model.NotificationMessageInfo

class NotificationMessageGeneratorImpl(
    private val cardNumberMasker: CardNumberMasker
) : NotificationMessageGenerator {

    override fun generateMessage(notificationMessageInfo: NotificationMessageInfo): String {
        val cardNumber = cardNumberMasker.mask(notificationMessageInfo.cardNumber, start = 0, end = 12)
        return "Уважаемый, ${notificationMessageInfo.name}!\n" +
                "Спешим Вам сообщить, что на карту $cardNumber\n" +
                "начислен cashback в размере ${notificationMessageInfo.cashback}\n" +
                "за категорию ${notificationMessageInfo.category}.\n" +
                "Спасибо за покупку ${notificationMessageInfo.transactionDate}"
    }
}