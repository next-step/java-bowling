package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    protected final int STRIKE_POINT = 10;

    private Strike strike;

    @BeforeEach
    void setUp() {
        strike = new Strike();
    }

    @DisplayName("프레임 점수 포맷된 출력")
    @Test
    void getPointsTest() {
        assertThat(strike.getPoints()).isEqualTo(STRIKE_POINT);
    }

    @DisplayName("첫 번째 넘어뜨린 핀 개수 출력")
    @Test
    void getFirstPinTest() {
        Pin pin = strike.getFirstPin();
        assertThat(pin.getFellPins()).isEqualTo(STRIKE_POINT);
    }

    @DisplayName("해당 프레임 종료 여부 출력")
    @Test
    void isFrameEndTest() {
        assertThat(strike.isFrameEnd()).isTrue();
    }

    @DisplayName("추상클래스 명 확인")
    @Test
    void isNameOfStateTest() {
        assertThat(strike.isNameOfState("Strike")).isTrue();
    }

    @DisplayName("추상클래스 명 출력")
    @Test
    void getNameOfStateTest() {
        assertThat(strike.getNameOfState()).isEqualTo("Strike");
    }

    @DisplayName("넘어뜨린 핀 결과 포맷되어 출력")
    @Test
    void getPointTest() {
        String pointSymbol = PointName.valueOfPointName(STRIKE_POINT, false);
        assertThat(strike.getPoint()).isEqualTo(String.format("%-4s", pointSymbol));
    }

    @DisplayName("Score 객체 출력")
    @Test
    void getScoreTest() {
        Score score = strike.getScore();
        assertThat(score.getScore()).isEqualTo(STRIKE_POINT);
    }
}
