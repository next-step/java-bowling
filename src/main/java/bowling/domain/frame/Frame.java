package bowling.domain.frame;

import java.util.Objects;

public class Frame {
    private final Inning inning;
    private final Pitch first;
    private final Pitch second;

    private Frame(Inning inning, Pitch first) {
        this(inning, first, new Pitch());
    }

    private Frame(Inning inning, Pitch first, Pitch second) {
        validateTotalScore(first, second);
        this.inning = inning;
        this.first = first;
        this.second = second;
    }

    public static Frame of(Inning inning, Pitch first) {
        return new Frame(inning, first, new Pitch());
    }

    public static Frame of(Inning inning, Pitch first, Pitch second) {
        return new Frame(inning, first, second);
    }

    public static Frame init() {
        return new Frame(Inning.first(), new Pitch());
    }

    private void validateTotalScore(Pitch first, Pitch second) {
        first.checkTotal(second);
    }

    public Frame recode(Pitch pitch) {
        if (first.nonRecode()) {//첫 번째 피칭
            return new Frame(inning, pitch);
        }
        if (first.isStrike()) {//첫 피칭이 스트라이크 일때, 새로운 프레임 (이닝)
            return new Frame(inning.nextInning(), pitch);
        }
        if (second.nonRecode()) {//현재 프레임의 2번째 피칭 기록
            return new Frame(inning, this.first, pitch);
        }
        return new Frame(inning.nextInning(), pitch);//다음 프레임에 첫번째 공 기록
    }

    public Inning inning() {
        if (first.isStrike()) {
            return inning.nextInning();
        }
        if (second.nonRecode()) {
            return inning;
        }
        return inning.nextInning();
    }

    public String score() {
        if (first.isStrike()) {
            return "   X  |";
        }
        return first.score(second);
    }

    public boolean finish() {
        if (second.nonRecode()) {
            return false;
        }
        if (first.isStrike()) {
            return false;
        }
        if (first.isSpare(second)) {
            return false;
        }
        return true;
    }

    public Inning getInning() {
        return inning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(inning, frame.inning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inning, first, second);
    }

}
