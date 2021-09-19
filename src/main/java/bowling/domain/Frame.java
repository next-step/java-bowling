package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    public static final int LAST_FRAME = 10;
    public static final int STRIKE = 10;
    private int order;
    private List<Pitch> pitches = new ArrayList<>();
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    public Frame(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }

    public void pitch(int score) {
        if (!isLastFrame()) {
            validate(score);
        }
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
        if (pitches.size() >= 2) {
            throw new IllegalArgumentException("한 프레임당 2개의 핏칭을 할 수 있습니다.");
        }
    }

    private boolean isLastFrame() {
        return order == LAST_FRAME;
    }

    public Pitch first() {
        return pitches.get(FIRST);
    }

    public Pitch second() {
        return pitches.get(SECOND);
    }

    public Pitch third() {
        if (!isLastFrame()) {
            throw new IllegalArgumentException("마지막 프레임에서만 세번째 핏칭을 할 수 있습니다.");
        }
        return pitches.get(THIRD);
    }

    public boolean remain() {
        if(isLastFrame()){
            return remainPitchInLastFrame();
        }
        if (isStrike()) {
            return false;
        }
        if (pitches.size() != 2) {
            return true;
        }
        return false;
    }

    private boolean remainPitchInLastFrame() {
        if (pitches.size() < 2) {
            return true;
        }
        if (pitches.size() == 2 && (first().score() == STRIKE || isSpare())) {
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
        if (pitches.size() == 1 && first().score() == STRIKE) {
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
