package com.seok2.bowling.state.domain;

public class Strike extends Finished {

    public static State of() {
        return new Strike();
    }
}
