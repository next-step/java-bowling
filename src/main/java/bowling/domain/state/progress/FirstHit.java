package bowling.domain.state.progress;

import bowling.domain.pin.Pins;
import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;

import static bowling.domain.state.end.End.GUTTER_SYMBOL;
import static bowling.domain.state.end.End.STRIKE_SYMBOL;

public class FirstHit extends Progress {
    private final Pins beforePins;

    private FirstHit(Pins beforePins) {
        this.beforePins = beforePins;
    }

    public static FirstHit from(Pins beforePins) {
        if (beforePins.isStrike()) {
            throw new IllegalArgumentException("이전 투구가 스트라이크면 FirstHit 상태를 생성할 수 없습니다.");
        }
        return new FirstHit(beforePins);
    }

    @Override
    public State run(Pitch pitch) {
        Pins pins = pitch.run();
        if (beforePins.isSpare(pins)) {
            return Spare.of(beforePins, pins);
        }
        return Miss.from(beforePins, pins);
    }

    @Override
    public Score score() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        return beforeScore.next(beforePins.size());
    }

    @Override
    public String symbol() {
        if (beforePins.isStrike()) {
            return STRIKE_SYMBOL;
        }
        if (beforePins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return Integer.toString(beforePins.size());
    }
}
