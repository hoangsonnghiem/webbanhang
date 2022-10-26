package com.example.demo;

public enum FormRegex {
    StringRegex("[a-zA-Z0-9]"),
    NumberRegex("[0-9]"),
    EmailRegex("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");

    public final String label;
    private FormRegex(String label)
    {
        this.label = label;
    }
}
