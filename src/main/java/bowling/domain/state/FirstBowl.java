package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class FirstBowl extends ProgressState {
    private static final String GUTTER_SYMBOL = "-";

    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        validatePins(firstPins);
        this.firstPins = firstPins;
    }

    private void validatePins(Pins firstPins) {
        if (firstPins.isStrike()) {
            throw new IllegalArgumentException(String.format("FirstBowl 상태는 첫번쨰 투구로 쓰러트린 핀이 10개 일 수 없습니다. 전달 받은 쓰러뜨린 Pins 갯수 %s", firstPins));
        }
    }

    @Override
    public FrameState bowl(Pins hitPins) {
        validateMaxHitPins(hitPins);

        if (firstPins.isSpare(hitPins)) {
            return new Spare(firstPins);
        }
        return new Miss(firstPins, hitPins);
    }

    private void validateMaxHitPins(Pins hitPins) {
        if (firstPins.isOverMaxHitPins(hitPins)) {
            throw new IllegalArgumentException(String.format("첫번쨰 투구로 쓰러뜨린 핀의 갯수 (%s)와 두번쨰 투구로 쓰러뜨린 핀의 갯수 (%s)의 합이 10을 넘을 수 없습니다.", firstPins, hitPins));
        }
    }

    @Override
    public String symbol() {
        if (firstPins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(firstPins.hitPins());
    }

    @Override
    public Score score() {
        return new Score(firstPins.hitPins(), Score.MIN_BONUS_CHANCE_COUNT);
    }

    @Override
    public Score calculateAdditionalScore(Score previousScore) {
        if (previousScore.isNoBonusChance()) {
            return previousScore;
        }
        return previousScore.addScore(firstPins.hitPins());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirstBowl firstBowl = (FirstBowl) o;

        return firstPins.equals(firstBowl.firstPins);
    }

    @Override
    public int hashCode() {
        return firstPins.hashCode();
    }
}