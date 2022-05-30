package bowling.domain.state;

import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.state.StateTest.FIRST;
import static bowling.domain.state.StateTest.MISS;
import static bowling.domain.state.StateTest.SECOND;
import static bowling.domain.state.StateTest.SPARE;
import static bowling.domain.state.StateTest.STRIKE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatesTest {
    public static final States DONE = new States(List.of(STRIKE, STRIKE, STRIKE));
    public static final States DOING = new States(List.of(STRIKE, STRIKE));

    @Test
    void isDone은_종료_여부를_반환한다() {
        assertAll(
                () -> assertFalse(new States(List.of(STRIKE)).isDone()),
                () -> assertFalse(new States(List.of(STRIKE, FIRST)).isDone()),
                () -> assertFalse(new States(List.of(STRIKE, STRIKE)).isDone()),
                () -> assertFalse(new States(List.of(FIRST, SPARE)).isDone()),
                () -> assertTrue(new States(List.of(FIRST, SECOND)).isDone()),
                () -> assertTrue(new States(List.of(FIRST, MISS)).isDone()),
                () -> assertTrue(new States(List.of(STRIKE, STRIKE, STRIKE)).isDone())
        );
    }
}
