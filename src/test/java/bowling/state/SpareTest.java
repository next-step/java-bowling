package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpareTest {
    private Spare spare;

    @BeforeEach
    void setUp() {
        final Pin downedPins = Pin.from(5);
        spare = Spare.from(downedPins);
    }

    @Test
    @DisplayName("Spare는 더이상 상태가 변할 수 없다")
    void nextPitch() {
        final Pin downedPins = Pin.from(Pin.MIN_COUNT_OF_PIN);

        Assertions.assertThatThrownBy(() -> {
            spare.nextPitch(downedPins);
        }).isInstanceOf(EndStateException.class);
    }

    @Test
    @DisplayName("Spare 처리한 Pin의 개수를 반환한다. 이전 투구에 쓰러트린 Pin의 개수도 알 수 있다")
    void getScore() {
        final int strikeScore = spare.getScore().stream()
                .reduce(0, Integer::sum);

        Assertions.assertThat(strikeScore).isEqualTo(5);
    }

    @Test
    @DisplayName("Spare는 Frame의 끝을 의미한다")
    void isEnd() {
        Assertions.assertThat(spare.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Spare를 칠 경우 레인에 Pin이 하나도 존재하지 않는다")
    void isClean() {
        Assertions.assertThat(spare.isClean()).isTrue();
    }
}
