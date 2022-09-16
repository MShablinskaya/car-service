package com.innowise.sharing.enums;

public enum Action {
    SUBMIT("RESERVED"),
    CONFIRM("IN_USE"),
    CANCEL("CANCELLED"),
    RETURN("RETURNED");

    private final String incomingState;

    Action(String incomingState){
        this.incomingState = incomingState;
    }

    public String getIncomingState() {
        return incomingState;
    }
}
