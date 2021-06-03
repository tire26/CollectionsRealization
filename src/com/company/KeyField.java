package com.company;

public class KeyField {

    private String key;
    private Integer value;
    private KeyField nextKeyField;

    public Integer getValue() {

        return value;
    }

    public void setValue(Integer value) {

        this.value = value;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public KeyField getNextKeyField() {

        return nextKeyField;
    }

    public void SetNextKeyField(KeyField nextKeyField) {

        this.nextKeyField = nextKeyField;
    }
}
