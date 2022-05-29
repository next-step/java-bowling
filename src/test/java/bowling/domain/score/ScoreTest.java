package bowling.domain.score;

import org.junit.jupiter.api.Test;

import static bowling.domain.State.PinTest.ZERO;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreTest {
    public static Score SCORABLE = Score.scorable();
    public static Score UNSCORABLE = Score.unScorable();

    @Test
    void bowl은_score를_더하고_left를_감소시킨_Score를_반환한다() {
        assertTrue(UNSCORABLE.bowl(ZERO).canScore());
    }

}
