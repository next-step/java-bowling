package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @Test
    @DisplayName("두번째 볼링 결과 - 스페어")
    void result() {
        Pin prevPin = Pin.bowl("3");
        Pin nextPin = Pin.bowl("7");
        State secondState = Spare.of(prevPin, nextPin);
        assertThat(secondState.isFinish()).isTrue();
    }

    @Test
    @DisplayName("두번째 볼링 결과 - 스페어")
    void resultMark() {
        Pin prevPin = Pin.bowl("3");
        Pin nextPin = Pin.bowl("7");
        State secondState = Spare.of(prevPin, nextPin);
        assertThat(secondState.toString()).isEqualTo("/");
    }
}
