package step4.domain.state;

import step4.domain.PinCount;
import step4.domain.Score;
import step4.domain.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Spare extends Finished{
    private static final String CHECK_SPARE = "두번에 걸쳐 쓰러뜨린 핀의 개수가 10이 아닙니다.";
    private final PinCount firstPinCount;
    private final PinCount secondPinCount;

    public Spare(PinCount firstPinCount, PinCount secondPinCount) {
        if (!firstPinCount.isSpare(secondPinCount)) {
            throw new IllegalArgumentException(CHECK_SPARE);
        }

        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

    @Override
    public Score score() {
        return Score.Spare();
    }

    @Override
    public Score addScore(Score score) {
        if (!score.isOpportunityLeft()) {
            return score;
        }

        score = score.add(firstPinCount.value());

        if (!score.isOpportunityLeft()) {
            return score;
        }

        score = score.add(secondPinCount.value());
        return score;
    }

    @Override
    public String marks() {
        List<String> marks = new ArrayList<>();
        marks.add(Symbol.of(this, firstPinCount, true));
        marks.add(Symbol.of(this, secondPinCount, false));
        return String.join("|", marks);
    }
}
