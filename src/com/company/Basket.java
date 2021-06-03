package com.company;

public class Basket {

    private int size = 0;
    private KeyField firstKeyField;

    public void incrementSize() {

        size++;
    }

    public void decrementSize() {

        size--;
    }

    public int getSize() {

        return size;
    }

    public KeyField getFirstKey() {

        return firstKeyField;
    }

    public void setFirstKey(KeyField keyField) {

        firstKeyField = keyField;
    }
}
