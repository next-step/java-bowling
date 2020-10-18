package bowling.frame;

import bowling.frame.state.Miss;
import bowling.frame.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = FinalFrame.init();
    }

    @Test
    @DisplayName("마지막 프레임 생성")
    void create() {
        frame = FinalFrame.create(10);
        assertThat(frame.getFrameNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("프레임 번호가 11로 증가하면 게임 종료")
    void finished() {
        frame.bowl("10");
        assertThat(frame.next().isFinish()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 Miss")
    void miss() {
        frame.bowl("8");
        frame.bowl("1");
        assertThat(frame.getState() instanceof Miss).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 Strike")
    void strike() {
        frame.bowl("10");
        assertThat(frame.getState() instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 Strike 시 추가 투구")
    void bonus() {
        frame.bowl("10");
        frame.bowl("10");
        frame.bowl("10");
        assertThat(frame.getState() instanceof Strike).isTrue();
    }
}
