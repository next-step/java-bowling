package bowling.domain;

import bowling.domain.Frame.DefaultFrame;
import bowling.domain.Frame.Frame;
import bowling.domain.Frame.LastFrame;
import bowling.domain.Score.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scoreboard {

    public static final int ROUND_START = 1;
    public static final int ROUND_END = 10;
    private final Name name;
    private final List<Frame> frames;

    public Scoreboard(Name name) {
        this.name = name;
        this.frames = new ArrayList<>();
        for (int round = ROUND_START; round < ROUND_END; round++) {
            this.frames.add(new DefaultFrame());
        }
        this.frames.add(new LastFrame());
    }

    public void addScore(Score score, Round round) {
        Frame frame = this.frames.get(round.index());
        frame.addScore(score);
        setBonusScore(score, round);
    }

    private void setBonusScore(Score score, Round round) {
        if (round.isSecondRound()) {
            addBonusScore(score, round.beforeRound());
        }
        if (round.isAfterSecondRound()) {
            addBonusScore(score, round.beforeRound());
            addBonusScore(score, round.beforeRound().beforeRound());
        }
    }

    public void addBonusScore(Score score, Round round) {
        Frame beforeRoundFrame = this.frames.get(round.index());
        if (beforeRoundFrame.isNotEndScoreAggregation()) {
            beforeRoundFrame.addBonusScore(score);
        }
    }

    public Frame frame(Round round) {
        return this.frames.get(round.index());
    }

    public Name name() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scoreboard that = (Scoreboard) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
