package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("점수 테스트")
public class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score();
    }


    @DisplayName("스트라이크나 스패어가 아닌 상태에서 다음 핀과의 합이 10을 넘은 경우 예외가 발생한다.")
    @Test
    void exception() {
        score.addPin(Pin.of(5));

        assertThatThrownBy(() -> score.addPin(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크인 상태에서 다음 핀은 10 이하로 던질 수 있다.")
    @Test
    void addPinStrike() {
        score.addPin(Pin.of(10));

        assertDoesNotThrow(() -> score.addPin(Pin.of(10)));
    }

    @DisplayName("스패어인 상태에서 다음 핀은 10 이하로 던질 수 있다.")
    @Test
    void addPinSpare() {
        score.addPin(Pin.of(5));
        score.addPin(Pin.of(5));

        assertDoesNotThrow(() -> score.addPin(Pin.of(10)));
    }
}
