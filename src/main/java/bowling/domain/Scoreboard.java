package bowling.domain;

import bowling.global.BowlingConst;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scoreboard {

    private final Name name;
    private final List<Frame> frames;

    public Scoreboard(Name name) {
        this.name = name;
        this.frames = new ArrayList<>();
        for (int round = BowlingConst.ROUND_START; round < BowlingConst.ROUND_END; round++) {
            this.frames.add(new DefaultFrame());
        }
        this.frames.add(new LastFrame());
    }

    public void addScore(Score score, Round round) {
        Frame frame = this.frames.get(round.index());
        frame.addScore(score);
    }

    public Frame frame(Round round) {
        return this.frames.get(round.index());
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(this.frames);
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
