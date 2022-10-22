package bowling.domain;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private final Bowling bowling;

    NormalFrame(Bowling bowling) {
        this.bowling = bowling;
    }

    public static NormalFrame init() {
        return new NormalFrame(Bowling.init());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;

        NormalFrame that = (NormalFrame) o;

        return Objects.equals(bowling, that.bowling);
    }

    @Override
    public int hashCode() {
        return bowling != null ? bowling.hashCode() : 0;
    }

    @Override
    public List<Bowling> bowlings() {
        return List.of(bowling);
    }

    @Override
    public Frame bowl(Score score) {
        return new NormalFrame(bowling.bowl(score));
    }

    @Override
    public boolean isFinished() {
        return bowling.isFinished();
    }
}
