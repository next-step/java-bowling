package com.seok2.bowling.state.domain;

import com.seok2.bowling.frame.domain.Score;
import com.seok2.bowling.pin.domain.Pin;
import java.util.Objects;

public class Spare extends Finished {

    private final Pin first;

    private Spare(Pin first) {
        this.first = first;
    }

    public static Spare of(Pin first) {
        return new Spare(first);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Spare)) {
            return false;
        }
        Spare spare = (Spare) o;
        return Objects.equals(first, spare.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }

    @Override
    public Score getScore() {
        return Score.TEN;
    }

    @Override
    public String view() {
        return first + "|/";
    }
}
