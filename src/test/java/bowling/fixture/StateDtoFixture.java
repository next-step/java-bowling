package bowling.fixture;

import bowling.domain.dto.StateDto;
import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstHit;
import bowling.domain.state.running.Ready;
import bowling.domain.state.running.Running;

public class StateDtoFixture {

    public StateDtoFixture() {
    }

    public static StateDto getReadyState() {
        State state = Ready.getInstance();
        return StateDto.of(state);
    }

    public static StateDto getFirstHitState() {
        State state = FirstHit.of(Pins.of(9));
        return StateDto.of(state);
    }

    public static StateDto getStrikeState() {
        State state = Strike.getInstance();
        return StateDto.of(state);
    }

    public static StateDto getSpareState() {
        State state = Spare.of(Pins.of(9), Pins.of(1));
        return StateDto.of(state);
    }

    public static StateDto getMissState() {
        State state = Miss.of(Pins.of(8), Pins.of(1));
        return StateDto.of(state);
    }

    public static class TestState extends Running {
        public TestState() {
        }

        @Override
        public State bowl(PinCount hitCount) {
            return null;
        }

        @Override
        public Pins getFirstPins() {
            return null;
        }

        @Override
        public Score getScore() {
            return null;
        }

        @Override
        public Score calculateBonusScore(Score score) {
            return null;
        }

        @Override
        protected Score calculateScoreForExtraBonusCount(Score beforeScore) {
            return null;
        }
    }
}
