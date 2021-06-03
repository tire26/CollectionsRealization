package com.company;

public class Element {

    private String value;
    private Element nextElement;

    public String getValue() {

        return  value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    public Element getNextElement() {

        return nextElement;
    }

    public void setNextElement(Element nextElement) {

        this.nextElement = nextElement;
    }
}
