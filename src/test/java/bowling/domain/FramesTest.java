package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @DisplayName("다음 프레임을 생성한다.")
    @Test
    void createNext() {
        Frames frames = new Frames();
        for (int i = 0; i < 7; i++) {
            frames.addPinCount(10);
        }

        assertThat(frames.createNext()).isExactlyInstanceOf(NormalFrame.class);
        frames.addPinCount(10);
        assertThat(frames.createNext()).isExactlyInstanceOf(FinalFrame.class);
    }

    @DisplayName("마지막 프레임이 종료하면 더이상 추가할 수 없다.")
    @Test
    void finish() {
        Frames frames = new Frames();
        for (int i = 0; i < 8; i++) {
            frames.addPinCount(10);
        }

        frames.addPinCount(10);
        frames.addPinCount(1);
        frames.addPinCount(2);
        assertThat(frames.isFinished()).isTrue();
    }
}
