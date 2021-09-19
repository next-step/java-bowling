package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FinalFrameTest {
    @Test
    @DisplayName("처음 주어진 핀을 MISS 하면 보너스 없음")
    public void miss() {
        FinalFrame frame = new FinalFrame();

        frame.record(ShotResult.FIVE);
        frame.record(ShotResult.TWO);
        assertThat(frame.isOver()).isTrue();
    }

    @Test
    @DisplayName("처음 주어진 핀을 두번째에 SPARE 하면 보너스 1회")
    public void spare() {
        FinalFrame frame = new FinalFrame();

        frame.record(ShotResult.FOUR);
        frame.record(ShotResult.SIX);
        assertThat(frame.isOver()).isFalse();

        frame.record(ShotResult.FIVE);
        assertThat(frame.isOver()).isTrue();
    }

    @Test
    @DisplayName("처음 주어진 핀을 첫번째에 STRIKE 하고 두번째에 SPARE 남기면 보너스 1회")
    public void strikeSpare() {
        FinalFrame frame = new FinalFrame();

        frame.record(ShotResult.TEN);
        assertThat(frame.isOver()).isFalse();

        frame.record(ShotResult.FIVE);
        assertThat(frame.isOver()).isFalse();

        frame.record(ShotResult.ONE);
        assertThat(frame.isOver()).isTrue();
    }

    @Test
    @DisplayName("처음 주어진 핀을 첫번째에 Strike 하고 두번째에 Strike 하면 보너스 1회")
    public void strikeTwice() {
        FinalFrame frame = new FinalFrame();

        frame.record(ShotResult.TEN);
        assertThat(frame.isOver()).isFalse();

        frame.record(ShotResult.TEN);
        assertThat(frame.isOver()).isFalse();

        frame.record(ShotResult.TEN);
        assertThat(frame.isOver()).isTrue();
    }
}
