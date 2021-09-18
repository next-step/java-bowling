package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int order;
    private List<Pitch> pitches = new ArrayList<>();
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    public Frame(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }

    public void pitch(int score) {
        validate(score);
        pitches.add(new Pitch(score));
    }

    private void validate(int score) {
        int scoreSum = score;
        if (!empty()) {
            scoreSum += first().score();
        }
        if (scoreSum > 10) {
            throw new IllegalArgumentException("한 프레임의 점수합이 10을 넘을 수 없습니다.");
        }
    }

    public Pitch first() {
        return pitches.get(FIRST);
    }

    public Pitch second() {
        return pitches.get(SECOND);
    }

    public boolean remain() {
        if (isStrike()) {
            return false;
        }
        if (pitches.size() != 2) {
            return true;
        }
        return false;
    }

    public boolean empty() {
        if (pitches.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean isStrike() {
        if (pitches.size() == 1 && first().score() == 10) {
            return true;
        }
        return false;
    }

    public boolean isSpare() {
        if (pitches.size() == 2 && scoreSum() == 10) {
            return true;
        }
        return false;
    }

    private int scoreSum() {
        return first().score() + second().score();
    }
}
