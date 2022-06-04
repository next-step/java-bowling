package bowling.domain.frame;

import bowling.exception.NotCreateFrameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    private Frame normalFrame;
    private Frame finalFrame;

    @BeforeEach
    void setUp() throws Exception {
        normalFrame = NormalFrame.initialize();

        finalFrame = NormalFrame.initialize();
        for (int i = 1; i < 10; i++) {
            finalFrame.bowling(10);
            finalFrame = finalFrame.next();
        }
    }

    @Test
    @DisplayName("1차 투구에서 모든 Pin 을 쓰러트리면 현재 프레임은 종료한다.")
    void finishFrame_strike() {
        normalFrame.bowling(10);
        assertThat(normalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("2차 투구까지 완료하면 현재 프레임은 종료한다.")
    void finishFrame() {
        normalFrame.bowling(5);
        normalFrame.bowling(2);

        assertThat(normalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임일 경우 다음 프레임을 생성하면 예외를 반환한다.")
    void invalidNext() {
        assertThatThrownBy(() -> finalFrame.next())
                .isInstanceOf(NotCreateFrameException.class)
                .hasMessage("다음 프레임을 생성할 수 없습니다. 현재 프레임: 10");
    }

}