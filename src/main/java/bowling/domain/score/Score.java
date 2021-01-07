package bowling.domain.score;

import bowling.domain.frame.DownedPin;
import bowling.domain.frame.FrameStatus;

import java.util.ArrayList;
import java.util.List;

public class Score {

    private final List<Integer> score = new ArrayList<>();
    private int remains = 0;

    public Score(List<DownedPin> initialRecord) {
        FrameStatus initialStatus = FrameStatus.decideStatus(initialRecord);

        if (initialStatus == FrameStatus.START || initialStatus == FrameStatus.ON_FRAME) {
            throw new IllegalArgumentException("아직 종료되지 않은 프레임 입니다. 점수 생성 최소한의 조건을 만족하지 못했습니다.");
        }

        for (DownedPin pin : initialRecord) {
            score.add(pin.getNumDownedPin());
        }

        if (initialStatus == FrameStatus.STRIKE) {
            remains = 2;
        }

        if (initialStatus == FrameStatus.SPARE) {
            remains = 1;
        }

        if (initialStatus == FrameStatus.MISS) {
            remains = 0;
        }
    }

    public void record(int additionalPins) {
        if (remains <= 0) {
            return;
        }

        score.add(additionalPins);
        remains -= 1;
    }

    public boolean hasFixedScore() {
        return remains == 0;
    }

    public int calculateScore() {
        return score.stream()
                .reduce(0, Integer::sum);
    }
}
