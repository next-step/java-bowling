package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalRollingResultTest {

    FinalRollingResult finalRollingResult;

    @BeforeEach
    void prepareRollingResultObject() {
        finalRollingResult = new FinalRollingResult();
    }

    @Test
    void isFinishTest() {
        String resultRecord = "2|5";
        finalRollingResult.bowl(new Pin(2));
        finalRollingResult.bowl(new Pin(5));

        assertTrue(finalRollingResult.isFinish());
        assertThat(finalRollingResult.currentFrameStatus()).isEqualTo(resultRecord);
    }

    @Test
    void isFinishExtraTest() {
        finalRollingResult.bowl(new Pin(10));
        finalRollingResult.bowl(new Pin(10));
        finalRollingResult.bowl(new Pin(10));

        assertTrue(finalRollingResult.isFinish());
    }

    @Test
    void descTest() {
        String turkey = "X|X|X";
        finalRollingResult.bowl(new Pin(10));
        finalRollingResult.bowl(new Pin(10));
        finalRollingResult.bowl(new Pin(10));

        assertThat(finalRollingResult.currentFrameStatus()).isEqualTo(turkey);
    }

    @Test
    void spareWithNormalBowlTest() {
        String resultRecord = "5|/|5";
        finalRollingResult.bowl(new Pin(5));
        finalRollingResult.bowl(new Pin(5));
        finalRollingResult.bowl(new Pin(5));

        assertThat(finalRollingResult.currentFrameStatus()).isEqualTo(resultRecord);
    }
}
