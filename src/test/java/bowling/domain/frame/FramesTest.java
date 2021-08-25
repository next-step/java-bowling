package bowling.domain.frame;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    Frames frames = Frames.of();

    @BeforeEach
    void setup() {
        for (int i = 0; i < 9; i++) {
            frames.bowl(Pins.of(10));
        }
    }

    @DisplayName("마지막 프레임에 거터이면 보너스 프레임이 존재하지 않는다.")
    @Test
    void final_gutter() {
        frames.bowl(Pins.of(0));
        frames.bowl(Pins.of(0));

        List<Frame> frameList = frames.getFrames();

        assertThat(frameList).hasSize(10);
        assertThat(frameList.get(9)).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("마지막 프레임에 거터이면 보너스 프레임이 존재하지 않는다.")
    @Test
    void final_miss() {
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(4));

        List<Frame> frameList = frames.getFrames();

        assertThat(frameList).hasSize(10);
        assertThat(frameList.get(9)).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("마지막 프레임에 스트라이크면 보너스 프레임이 존재한다.")
    @Test
    void final_strike() {
        frames.bowl(Pins.of(10));

        List<Frame> frameList = frames.getFrames();

        assertThat(frameList).hasSize(10);
        assertThat(frameList.get(9)).isInstanceOf(BonusFrame.class);
    }

    @DisplayName("마지막 프레임에 거터이면 보너스 프레임이 존재한다.")
    @Test
    void final_spare() {
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(7));

        List<Frame> frameList = frames.getFrames();

        assertThat(frameList).hasSize(10);
        assertThat(frameList.get(9)).isInstanceOf(BonusFrame.class);
    }
}