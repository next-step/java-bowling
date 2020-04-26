package bowling.domain.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

public class LastFrameBowl extends Finished {
    private List<Pins> rolls = new ArrayList<>();
    private int count = 2;

    public LastFrameBowl(boolean bonusFlag) {
        if (bonusFlag) {
            ++count;
        }
    }

    @Override
    public State bowl(int felledPins) {
        if (rolls.size() == count) {
            throw new UnsupportedOperationException("공을 투구할 횟수를 초과하였습니다.");
        }
        Pins pins = Pins.bowl(felledPins);
        rolls.add(pins);
        return this;
    }

    @Override
    public String getDesc() {
        return rolls.stream()
                    .map(pins -> displayText(pins))
                    .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Score getScore() {
        if (rolls.size() < count) {
            throw new CannotCalculateException("계산 할 수 있는 상태가 아닙니다");
        }
        int totalPins = 0;
        for (Pins roll : rolls) {
            totalPins += roll.getFelledPins();
        }
        return new Score(totalPins);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        int left = score.getLeft();
        if (rolls.size() < left) {
            throw new CannotCalculateException("계산 할 수 있는 상태가 아닙니다");
        }
        for (int i = 0; i < left; i++) {
            score = rolls.get(i).sumScore(score);
        }
        return score;
    }

    private String displayText(Pins pins) {
        if (pins.isStrike()) {
            return STRIKE;
        }
        return pins.getDisplayPins(pins.getFelledPins());
    }
}
