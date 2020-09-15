package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalRollingResultTest {

    @Test
    void isFinishTest() {
        FinalRollingResult finalRollingResult = new FinalRollingResult();
        finalRollingResult.bowl(2);
        finalRollingResult.bowl(5);

        assertTrue(finalRollingResult.isFinish());
    }

    @Test
    void isFinishExtraTest() {
        FinalRollingResult finalRollingResult = new FinalRollingResult();
        finalRollingResult.bowl(10);
        finalRollingResult.bowl(10);
        finalRollingResult.bowl(10);

        assertTrue(finalRollingResult.isFinish());
    }

    @Test
    void descTest() {
        String turkey = "X|X|X";
        FinalRollingResult finalRollingResult = new FinalRollingResult();
        finalRollingResult.bowl(10);
        finalRollingResult.bowl(10);
        finalRollingResult.bowl(10);

        assertThat(finalRollingResult.desc()).isEqualTo(turkey);
    }
}
