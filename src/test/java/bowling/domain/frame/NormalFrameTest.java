package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private Frame normalFrame;

    @BeforeEach
    void setup() {
        normalFrame = NormalFrame.initialize();
    }

    @ParameterizedTest(name = "{0} Frame: NormalFrame")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void nextFrame(int number) throws Exception {
        for (int i = 1; i < number; i++) {
            normalFrame.bowling(10);
            normalFrame = normalFrame.next();
        }

        assertThat(normalFrame.content().frameNo()).isEqualTo(number);
        assertThat(normalFrame).isInstanceOf(NormalFrame.class);
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
}
