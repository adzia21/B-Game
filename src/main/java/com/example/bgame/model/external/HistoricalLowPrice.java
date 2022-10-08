package com.example.bgame.model.external;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class HistoricalLowPrice{
    private String country;
    private Date date;
    private double price;
    private boolean isLow;
}
