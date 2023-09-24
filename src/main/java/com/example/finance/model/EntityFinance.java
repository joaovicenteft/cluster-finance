package com.example.finance.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
//{
//        "timeMonth": 1,
//        "inflationMean": 6.63
//}
public class EntityFinance {

    private Long id;
    private List<String> values;
    private int timeMonth;
    private double inflationMean;
    private double fullMoney;
    private double rescisionMoney;
    private double liquidMoney;

    public EntityFinance() {
        super();
    }

    public EntityFinance(Long id, int timeMonth, double inflationMean, double fullMoney) {
        this.id = id;
        //this.values = values;
        this.timeMonth = timeMonth;
        this.inflationMean = inflationMean;
        this.fullMoney = fullMoney;
        //this.rescisionMoney = rescisionMoney;
        //this.liquidMoney = liquidMoney;
    }

    public int getTimeMonth() {
        return timeMonth;
    }

    public void setTimeMonth(int timeMonth) {
        this.timeMonth = timeMonth;
    }

    public double getInflationMean() {
        return inflationMean;
    }

    public void setInflationMean(double inflationMean) {
        this.inflationMean = inflationMean;
    }

    public double getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(double fullMoney) {
        this.fullMoney = fullMoney;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
