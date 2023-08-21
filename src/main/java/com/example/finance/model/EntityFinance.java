package com.example.finance.model;

public class EntityFinance {

    private Long id;
    private double initialMoney;
    private double income;

    public EntityFinance() {

    }

    public EntityFinance(Long id, double initialMoney) {
        this.id = id;
        this.initialMoney = initialMoney;
        this.income = income;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(double initialMoney) {
        this.initialMoney = initialMoney;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
