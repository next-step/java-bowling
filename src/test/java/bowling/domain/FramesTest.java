package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();
        int size = 8;
        for (int i = 0; i < size; i++) {
            frames.addPinCount(10);
        }
    }

    @DisplayName("다음 프레임을 생성한다.")
    @Test
    void createNext() {
        assertThat(frames.getLastFrame())
                .isExactlyInstanceOf(NormalFrame.class);
        frames.addPinCount(10);
        assertThat(frames.getLastFrame()).isExactlyInstanceOf(FinalFrame.class);
    }

    @DisplayName("마지막 프레임이 종료하면 더이상 추가할 수 없다.")
    @Test
    void finish() {
        frames.addPinCount(10);
        frames.addPinCount(1);
        frames.addPinCount(2);
        assertThat(frames.isFinished()).isTrue();
    }
}
