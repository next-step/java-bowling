package com.seok2.bowling.state.domain;

import com.seok2.bowling.frame.domain.Score;

public class Gutter extends Finished {

    public static State of() {
        return new Gutter();
    }

    @Override
    public Score getScore() {
        return Score.ZERO;
    }

    @Override
    public String view() {
        return "-";
    }

    @Override
    public Score calculate(Score base) {
        base = base.add(Score.ofGutter());
        if (base.isPending()) {
            base = base.add(Score.ofGutter());
        }
        return base;
    }

    @Override
    public Score getScore() {
        return Score.ofGutter();
    }
}
