package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.users.response.CurrencyResponse;
import com.learn.booking.bookingsystem.db.model.Currency;
import org.springframework.stereotype.Service;

@Service
public class CurrencyMapper {

    public CurrencyResponse currencyToCurrencyResponse(Currency currency) {
        return CurrencyResponse.builder()
            .uuid(currency.getUuid())
            .name(currency.getName())
            .shortName(currency.getShortName())
            .exchangeRate(currency.getExchangeRate())
            .build();
    }

    public CurrencyResponse mongoCurrencyToCurrencyResponse(com.learn.booking.bookingsystem.db.mongo.Currency currency) {
        return CurrencyResponse.builder()
            .uuid(currency.getUuid())
            .name(currency.getName())
            .shortName(currency.getShortName())
            .exchangeRate(currency.getExchangeRate())
            .build();
    }

    public com.learn.booking.bookingsystem.db.mongo.Currency currencyResponseToMongoCurrency(CurrencyResponse currency) {
        return com.learn.booking.bookingsystem.db.mongo.Currency.builder()
            .uuid(currency.getUuid())
            .name(currency.getName())
            .shortName(currency.getShortName())
            .exchangeRate(currency.getExchangeRate())
            .build();
    }
}
