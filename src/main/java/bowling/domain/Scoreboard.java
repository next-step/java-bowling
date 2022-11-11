package bowling.domain;

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
        frame.minusChance();
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
