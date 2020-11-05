package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    @DisplayName("투구 종료 여부 확인 - 종료")
    void isFinished() {
        Pin prevPin = Pin.bowl("3");
        Pin nextPin = Pin.bowl("7");
        State secondState = Spare.of(prevPin, nextPin);
        assertThat(secondState.isFinish()).isTrue();
    }

    @Test
    @DisplayName("두번째 볼링 결과 마크 - 스페어")
    void resultMark() {
        Pin prevPin = Pin.bowl("3");
        Pin nextPin = Pin.bowl("7");
        State secondState = Spare.of(prevPin, nextPin);
        assertThat(secondState.getBowlResults().get(0)).isEqualTo("3");
        assertThat(secondState.getBowlResults().get(1)).isEqualTo("/");
    }

    @Test
    @DisplayName("첫번째 볼링결과가 거터, 두번째 볼링결과가 스페어라면..")
    void resultIsGutterAndSpare() {
        Pin prevPin = Pin.bowl("0");
        Pin nextPin = Pin.bowl("10");
        State state = Spare.of(prevPin, nextPin);
        assertThat(state.getBowlResults().get(0)).isEqualTo("-");
        assertThat(state.getBowlResults().get(1)).isEqualTo("/");
    }
}
