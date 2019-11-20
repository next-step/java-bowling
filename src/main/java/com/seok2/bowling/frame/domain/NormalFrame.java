package com.seok2.bowling.frame.domain;

import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.state.domain.Ready;
import com.seok2.bowling.state.domain.State;
import java.util.Optional;

public class NormalFrame implements Frame {

    private State state = Ready.of();
    private FrameScore score;

    public static Frame of() {
        return new NormalFrame();
    }

    public void createScore(ScorePublisher publisher) {
        if (!isEnd()) {
            throw new IllegalArgumentException("아직 종료 되진 않은 프레임입니다.");
        }
        score = FrameScore.of(publisher, state);
    }

    @Override
    public void roll(Pin felled) {
        this.state = state.roll(felled);
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public FrameScore getScore() {
        return Optional.ofNullable(score).orElse(FrameScore.PENDING);
    }

    protected State getState() {
        return state;
    }
}
