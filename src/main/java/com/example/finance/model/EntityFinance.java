package com.example.finance.model;

import java.util.List;

public class EntityFinance {

    private Long id;
    private List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public EntityFinance(Long id, List<String> values) {
        this.id = id;
        this.values = values;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
