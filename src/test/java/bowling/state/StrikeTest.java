package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {
    private Pin downedPins;
    private Strike strike;

    @BeforeEach
    void setUp() {
        downedPins = Pin.from(Pin.MAX_COUNT_OF_PIN);
        strike = Strike.init();
    }

    @Test
    @DisplayName("Strike는 더이상 상태가 변할 수 없다")
    void nextPitch() {
        Assertions.assertThatThrownBy(() -> {
            strike.nextPitch(downedPins);
        }).isInstanceOf(EndStateException.class);
    }

    @Test
    @DisplayName("Strike는 Pin을 10개 쓰러트림을 의미한다")
    void getScore() {
        final int strikeScore = strike.getScore().stream()
                .reduce(0, Integer::sum);

        Assertions.assertThat(strikeScore).isEqualTo(10);
    }

    @Test
    @DisplayName("Strike는 Frame의 끝을 의미한다")
    void isEnd() {
        Assertions.assertThat(strike.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Strike을 칠 경우 레인에 Pin이 하나도 존재하지 않는다")
    void isClean() {
        Assertions.assertThat(strike.isClean()).isTrue();
    }
}
