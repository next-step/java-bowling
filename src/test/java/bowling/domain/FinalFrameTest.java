package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("FinalFrame 클래스 테스트")
public class FinalFrameTest {

    @DisplayName("볼링 결과를 갖는다.")
    @Test
    void bowl() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(new Pin(10));

        assertThat(finalFrame.getStates().getStates().get(0)).isEqualTo(State.STRIKE);
        assertThat(finalFrame.getPin()).isEqualTo(new Pin(10));
    }

    @DisplayName("현재 프레임 종료 여부를 반환할 수 있다.")
    @Test
    void isEndFrame() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(new Pin(10));
        finalFrame.bowl(new Pin(10));
        finalFrame.bowl(new Pin(10));

        boolean actual = finalFrame.isEndFrame();

        assertThat(actual).isFalse();
    }

    @DisplayName("BowlingGame 종료 여부를 반환할 수 있다.")
    @Test
    void isEndGame() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(new Pin(10));
        finalFrame.bowl(new Pin(10));
        finalFrame.bowl(new Pin(10));

        boolean actual = finalFrame.isEndGame();

        assertThat(actual).isTrue();
    }

    @DisplayName("다음 프레임 요청 시 예외를 반환한다.")
    @Test
    void getNextFrame() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> new FinalFrame().getNextFrame(1));
    }

    @Test
    void getScore() {
        int firstPin = 10;
        int secondPin = 10;
        int thirdPin = 2;
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(new Pin(firstPin));
        finalFrame.bowl(new Pin(secondPin));
        finalFrame.bowl(new Pin(thirdPin));

        int score = finalFrame.getScore();

        assertThat(score).isEqualTo(firstPin + secondPin + thirdPin);
    }
}
