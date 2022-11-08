package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scoreboard {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;
    private final Name name;
    private final List<Frame> frames;

    public Scoreboard(Name name) {
        this.name = name;
        this.frames = new ArrayList<>();
        for (int i = FIRST_ROUND; i < LAST_ROUND; i++) {
            this.frames.add(new DefaultFrame());
        }
        this.frames.add(new LastFrame());
    }

    public void addScore(Score score, Round round) {
        this.frames.get(round.index()).addScore(score);
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(this.frames);
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
