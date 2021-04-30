package bowling.ui;

import java.util.StringJoiner;

public class FrameScore {
    private final StringJoiner stringJoiner;
    private boolean opened;

    public FrameScore() {
        stringJoiner = new StringJoiner("|");
        opened = true;
    }

    public void addScore(String score) {
        if (opened) {
            stringJoiner.add(score);
            return;
        }
        throw new IllegalStateException("스코어가 확정되어 스코어를 추가할 수 없습니다.");
    }

    public String scoreConfirm() {
        opened = false;
        return stringJoiner.toString();
    }
}
