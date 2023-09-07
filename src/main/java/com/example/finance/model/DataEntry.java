package com.example.finance.model;

import java.util.ArrayList;
import java.util.List;

public class DataEntry {
    private List<String> values;

    public String getValues(int index) {
        return values.get(index);
    }

    public void putValues(int index, String data) {
        values.set(index, data);
    }

    public void deleteValues(int index) {
        values.remove(index);
    }

    // Constructor to initialize a data entry
    public DataEntry(String... values) {
        this.values = new ArrayList<>();
        for (String value : values) {
            this.values.add(value);
        }
    }

    // Override toString to provide a meaningful representation
    @Override
    public String toString() {
        return values.toString();
    }
}

