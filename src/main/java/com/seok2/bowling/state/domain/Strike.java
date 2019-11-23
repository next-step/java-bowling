package com.seok2.bowling.state.domain;

import com.seok2.bowling.frame.domain.Score;

public class Strike extends Finished {

    public static State of() {
        return new Strike();
    }

    @Override
    public Score getScore() {
        return Score.TEN;
    }

    @Override
    public String view() {
        return "X";
    }

    @Override
    public Score calculate(Score base) {
        return base.add(Score.ofStrike());
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }
}
