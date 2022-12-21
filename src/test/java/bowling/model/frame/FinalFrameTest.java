package bowling.model.frame;

import bowling.model.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    FinalFrame finalFrame;

    @BeforeEach
    void init() {
        finalFrame = new FinalFrame();
    }

    @DisplayName("게임 진행 중")
    @ParameterizedTest
    @CsvSource(value = {"10,10", "0,10", "10,0"})
    void isFinishedFalse(int first, int second) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("3번 쳤을 때 게임 끝인 경우")
    @ParameterizedTest
    @CsvSource(value = {"10,10,10", "0,10,10", "10,0,10", "10,3,3"})
    void isFinishedTrue1(int first, int second, int third) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));
        finalFrame.bowl(Pin.of(third));

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @DisplayName("2번 쳤을 때 게임 끝인 경우")
    @ParameterizedTest
    @CsvSource(value = {"0,5"})
    void isFinishedTrue2(int first, int second) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));

        assertThat(finalFrame.isFinished()).isTrue();
    }
}
