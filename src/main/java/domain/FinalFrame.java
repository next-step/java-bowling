package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FinalFrame {
    static final int FIRST_TRIAL = 0;
    static final int STRIKE_PINS = 10;
    static final String ALERT_END_OF_GAME = "게임이 끝났습니다.";

    private final List<Pins> roundResult;

    private FinalFrame(List<Pins> roundResult) {
        this.roundResult = roundResult;
    }

    public static FinalFrame from(List<Pins> roundResult) {
        return new FinalFrame(roundResult);
    }

    public FinalFrame fillFrame(Pins fallenPins) {
        if (roundResult.size() < 2 || bonusBowlable()) {
            roundResult.add(fallenPins);
            return this;
        }
        throw new IllegalArgumentException(ALERT_END_OF_GAME);
        //TODO: 게임종료를 판단한 후에 fillFrame 메서드를 수행하는 것 고려해보기
    }

    boolean bonusBowlable() {
        return roundResult.size() == 2 && (isStrike() || isSpare());
    }

    private boolean isStrike() {
        return roundResult.get(FIRST_TRIAL).equals(Pins.from(STRIKE_PINS));
    }

    private boolean isSpare() {
        return sumRoundResults() == STRIKE_PINS;
    }

    private int sumRoundResults() {
        return roundResult.stream()
                .mapToInt(Pins::getFallenPins) //TODO: getter 개선하기
                .sum();
    }

    public boolean isMatch(FinalFrame finalFrame) {
        return this.equals(finalFrame);
    }

    public List<Pins> getRoundResult() {
        return Collections.unmodifiableList(roundResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(roundResult, that.roundResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundResult);
    }
}
