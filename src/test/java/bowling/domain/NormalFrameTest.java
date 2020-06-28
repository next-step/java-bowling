package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("NormalFrame 클래스 테스트")
public class NormalFrameTest {

    @DisplayName("볼링 결과를 갖는다.")
    @Test
    void bowl() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(new Pin(10));

        assertThat(normalFrame.getStates().getStates().get(0)).isEqualTo(State.STRIKE);
        assertThat(normalFrame.getPin()).isEqualTo(new Pin(10));
    }


    @DisplayName("현재 프레임 종료 여부를 반환할 수 있다.")
    @Test
    void isEndFrame() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(new Pin(10));

        boolean actual = normalFrame.isEndFrame();

        assertThat(actual).isTrue();
    }

    @DisplayName("다음 프레임을 생성 후 반환할 수 있다.")
    @Test
    void getNextFrame() {
        NormalFrame normalFrame = new NormalFrame();

        Frame actual1 = normalFrame.getNextFrame(8);
        Frame actual2 = normalFrame.getNextFrame(9);

        assertThat(actual1).isInstanceOf(NormalFrame.class);
        assertThat(actual2).isInstanceOf(FinalFrame.class);
    }

    @Test
    void getScore_miss() {
        int firstPin = 2;
        int secondPin = 7;
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(new Pin(firstPin));
        normalFrame.bowl(new Pin(secondPin));

        int score = normalFrame.getScore();

        assertThat(score).isEqualTo(firstPin + secondPin);
    }

    @Test
    void getScore_spare() {
        int firstPin = 2;
        int secondPin = 8;
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(new Pin(firstPin));
        normalFrame.bowl(new Pin(secondPin));
        normalFrame.getNextFrame(2);

        int score = normalFrame.getScore();

        assertThat(score).isEqualTo(Frame.WAITING_CALCULATION);
    }

    @Test
    void getScore_strike() {
        int firstPin = 10;
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(new Pin(firstPin));
        normalFrame.getNextFrame(2);

        int score = normalFrame.getScore();

        assertThat(score).isEqualTo(Frame.WAITING_CALCULATION);
    }
}
