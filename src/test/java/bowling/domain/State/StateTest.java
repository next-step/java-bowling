package bowling.domain.State;

import bowling.domain.score.ScoreTest;
import org.junit.jupiter.api.Test;

import static bowling.domain.State.PinTest.NINE;
import static bowling.domain.State.PinTest.ONE;
import static bowling.domain.State.PinTest.TEN;
import static bowling.domain.State.PinTest.ZERO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StateTest {
    public static final State READY = State.ready();
    public static final State GUTTER = new Gutter();
    public static final State FIRST = new First(ONE);
    public static final State SECOND = new Second(ONE, FIRST);
    public static final State SPARE = new Spare(TEN, GUTTER);
    public static final State MISS = new Miss(FIRST);
    public static final State STRIKE = new Strike(TEN);

    @Test
    void isDone은_프레임_종료_여부를_반환한다() {
        assertAll(
                () -> assertFalse(READY.isDone()),
                () -> assertFalse(FIRST.isDone()),
                () -> assertFalse(GUTTER.isDone()),
                () -> assertTrue(MISS.isDone()),
                () -> assertTrue(SECOND.isDone()),
                () -> assertTrue(SPARE.isDone()),
                () -> assertTrue(STRIKE.isDone())
        );
    }

    @Test
    void bowl은_다음_상태를_반환한다() {
        assertAll(
                () -> assertInstanceOf(Gutter.class, READY.bowl(ZERO)),
                () -> assertInstanceOf(First.class, READY.bowl(ONE)),
                () -> assertInstanceOf(Strike.class, READY.bowl(TEN)),
                () -> assertInstanceOf(Second.class, FIRST.bowl(ONE)),
                () -> assertInstanceOf(Miss.class, FIRST.bowl(ZERO)),
                () -> assertInstanceOf(Spare.class, FIRST.bowl(NINE))
        );
    }

    @Test
    void score는_score를_반환한다() {
        assertAll(
                () -> assertTrue(SECOND.score().canScore()),
                () -> assertTrue(MISS.score().canScore()),
                () -> assertFalse(READY.score().canScore()),
                () -> assertFalse(GUTTER.score().canScore()),
                () -> assertFalse(FIRST.score().canScore()),
                () -> assertFalse(SPARE.score().canScore()),
                () -> assertFalse(STRIKE.score().canScore())
        );
    }

    @Test
    void score는_bowl된_score를_반환한다() {
        assertTrue(FIRST.score(ScoreTest.UNSCORABLE).canScore());
    }
}
