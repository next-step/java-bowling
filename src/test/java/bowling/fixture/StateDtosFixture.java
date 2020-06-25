package bowling.fixture;

import bowling.domain.dto.StateDtos;
import bowling.domain.pin.Pins;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstHit;
import bowling.domain.state.running.Ready;

import java.util.Arrays;
import java.util.Collections;

public class StateDtosFixture {

    private static final Ready READY = Ready.getInstance();
    private static final FirstHit FIRSTHIT = FirstHit.of(Pins.of(9));
    private static final Strike STRIKE = Strike.getInstance();
    private static final Spare SPARE = Spare.of(Pins.of(9), Pins.of(1));
    private static final Miss MISS = Miss.of(Pins.of(8), Pins.of(1));

    public StateDtosFixture() {
    }

    public static StateDtos getThreeStrikeFinalFrame() {
        return StateDtos.of(Arrays.asList( STRIKE, STRIKE, STRIKE, READY ));
    }

    public static StateDtos getStrikeMissFinalFrame() {
        return StateDtos.of(Arrays.asList( STRIKE, MISS ));
    }

    public static StateDtos getSpareStrikeFinalFrame() {
        return StateDtos.of(Arrays.asList( SPARE, STRIKE, READY ));
    }

    public static StateDtos getSpareHitFinalFrame() {
        return StateDtos.of(Arrays.asList( SPARE, FIRSTHIT ));
    }

    public static StateDtos getReadyState() {
        return StateDtos.of(Collections.singletonList(READY));
    }

    public static StateDtos getFirstHitState() {
        return StateDtos.of(Collections.singletonList(FIRSTHIT));
    }

    public static StateDtos getStrikeState() {
        return StateDtos.of(Collections.singletonList(STRIKE));
    }

    public static StateDtos getSpareState() {
        return StateDtos.of(Collections.singletonList(SPARE));
    }

    public static StateDtos getMissState() {
        return StateDtos.of(Collections.singletonList(MISS));
    }
}
