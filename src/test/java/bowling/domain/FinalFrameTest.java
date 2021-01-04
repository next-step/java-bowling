package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest extends FrameTest {

    @BeforeEach
    void setUp(){
        frame = Frame.first()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext();
    }

    @DisplayName("10 번째 프래임은 LastFrame 이다")
    @Test
    void should_be_LastFrame(){
        Frame last = Frame.first()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext()
                .createNext();

        assertThat(last instanceof FinalFrame).isTrue();
    }

    @DisplayName("마지막 프레임의 다음 프레임은 null 을 return 한다")
    @Test
    void nextIsNull(){
        assertThat(frame.createNext()).isNull();
    }

    @DisplayName("1구 후 점수를 알 수 없다")
    @Test
    void unknownScore(){
        frame.mark(8);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("2구 후 miss 면 점수를 알 수 있다")
    @Test
    void missScore(){
        frame.mark(8);
        frame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(9));
    }

    @DisplayName("Spare 후 점수를 알 수 없다")
    @Test
    void spare1(){
        frame.mark(9);
        frame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Spare 후 1구를 던지면 점수를 알 수 있다")
    @Test
    void spare2(){
        frame.mark(9);
        frame.mark(1);
        frame.mark(8);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(18));
    }

    @DisplayName("Strike 후 점수를 알 수 없다")
    @Test
    void strike1(){
        frame.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Strike 후 1구(strike)를 던져도 점수를 알 수 없다")
    @Test
    void strike2() {
        frame.mark(10);
        frame.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Strike 후 2구(strike, strike)를 던지면 점수를 알 수 있다")
    @Test
    void strike3() {
        frame.mark(10);
        frame.mark(10);
        frame.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(30));
    }

    @DisplayName("Strike 후 2구(spare)를 던지면 점수를 알 수 있다")
    @Test
    void strike4() {
        frame.mark(10);
        frame.mark(9);
        frame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(20));
    }

    @DisplayName("Strike 후 2구(miss)를 던지면 점수를 알 수 있다")
    @Test
    void strike5() {
        frame.mark(10);
        frame.mark(8);
        frame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(19));
    }

}