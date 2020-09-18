package bowling.domain.rolling;

import bowling.domain.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalRollingTest {

    FinalRolling finalRolling;

    @BeforeEach
    void prepareRollingResultObject() {
        finalRolling = new FinalRolling();
    }

    @Test
    void isFinishTest() {
        String resultRecord = "2|5";
        finalRolling.bowl(new Pin(2));
        finalRolling.bowl(new Pin(5));

        assertTrue(finalRolling.isFinish());
        assertThat(finalRolling.currentFrameStatus()).isEqualTo(resultRecord);
    }

    @Test
    void isFinishExtraTest() {
        finalRolling.bowl(new Pin(10));
        finalRolling.bowl(new Pin(10));
        finalRolling.bowl(new Pin(10));

        assertTrue(finalRolling.isFinish());
    }

    @Test
    void descTest() {
        String turkey = "X|X|X";
        finalRolling.bowl(new Pin(10));
        finalRolling.bowl(new Pin(10));
        finalRolling.bowl(new Pin(10));

        assertThat(finalRolling.currentFrameStatus()).isEqualTo(turkey);
    }

    @Test
    void spareWithNormalBowlTest() {
        String resultRecord = "5|/|5";
        finalRolling.bowl(new Pin(5));
        finalRolling.bowl(new Pin(5));
        finalRolling.bowl(new Pin(5));

        assertThat(finalRolling.currentFrameStatus()).isEqualTo(resultRecord);
    }

    @Test
    void strikeAndGutterAndSpareBowlTest() {
        String resultRecord = "X|-|/";
        finalRolling.bowl(new Pin(10));
        finalRolling.bowl(new Pin(0));
        finalRolling.bowl(new Pin(10));

        assertThat(finalRolling.currentFrameStatus()).isEqualTo(resultRecord);
    }
}
