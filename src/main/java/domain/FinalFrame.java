package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FinalFrame {
    static final int FIRST_TRIAL = 0;
    static final int STRIKE_PINS = 10;
    static final String ALERT_END_OF_GAME = "게임이 끝났습니다.";
    static final int MAXIMUM_SCORE_FOR_A_ROUND = 10;
    static final String ALERT_EXCEED_MAXIMUM_SCORE = "두번째 투구의 결과는 초구 이후 남은 핀의 개수를 초과할 수 없습니다.";

    private final List<Pins> roundResult;

    private FinalFrame(List<Pins> roundResult) {
        this.roundResult = roundResult;
    }

    public static FinalFrame from(List<Pins> roundResult) {
        return new FinalFrame(roundResult);
    }

    public FinalFrame fillFrame(Pins fallenPins) {
        if (roundResult.isEmpty()) {
            roundResult.add(fallenPins);
            return this;
        }
        if (!isStrike() && firstResult() + fallenPins.getFallenPins() > MAXIMUM_SCORE_FOR_A_ROUND) { //TODO: 조건문 개선
            throw new IllegalArgumentException(ALERT_EXCEED_MAXIMUM_SCORE);
        }
        if (roundResult.size() < 2 || bonusBowlable()) {
            roundResult.add(fallenPins);
            return this;
        }
        throw new IllegalArgumentException(ALERT_END_OF_GAME);
        //TODO: 게임종료를 판단한 후에 fillFrame 메서드를 수행하는 것 고려해보기
    }

    private int firstResult() {
        return roundResult.get(FIRST_TRIAL).getFallenPins(); //TODO: RoundResult 객체 만들어서 위임하기
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
