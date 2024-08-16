package com.challenge.usercreation.model;

import com.challenge.usercreation.model.jpa.PhoneJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    private String number;

    private String citycode;

    private String countrycode;

    public PhoneJpa toJpa() {
        return PhoneJpa.builder()
                .number(this.number)
                .cityCode(this.citycode)
                .countryCode(this.countrycode)
                .build();
    }

    public static Phone fromJpa(PhoneJpa phoneJpa) {
        return Phone.builder()
                .number(phoneJpa.getNumber())
                .citycode(phoneJpa.getCityCode())
                .countrycode(phoneJpa.getCountryCode())
                .build();
    }
}
