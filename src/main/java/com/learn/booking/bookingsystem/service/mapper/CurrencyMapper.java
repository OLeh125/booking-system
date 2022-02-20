package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.users.response.CurrencyResponse;
import com.learn.booking.bookingsystem.db.model.Currency;
import org.springframework.stereotype.Service;

@Service
public class CurrencyMapper {

    public CurrencyResponse currencyToCurrencyResponse(Currency currency) {
        return CurrencyResponse.builder()
            .id(currency.getId())
            .uuid(currency.getUuid())
            .name(currency.getName())
            .shortName(currency.getShortName())
            .exchangeRate(currency.getExchangeRate())
            .build();
    }
}
