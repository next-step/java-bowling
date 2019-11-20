package com.seok2.bowling.state.domain;

public class Gutter extends Finished {

    public static State of() {
        return new Gutter();
    }

    @Override
    public String view() {
        return "-";
    }
}
