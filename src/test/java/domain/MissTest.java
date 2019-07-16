package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    protected final int FIRST_POINT = 2;
    protected final int SECOND_POINT = 3;

    private Miss miss;

    @BeforeEach
    void setUp() {
        miss = new Miss(Pin.of(FIRST_POINT), Pin.of(SECOND_POINT));
    }

    @DisplayName("프레임 점수 포맷된 출력")
    @Test
    void getPointsTest() {
        assertThat(miss.getPoints()).isEqualTo(FIRST_POINT+SECOND_POINT);
    }

    @DisplayName("첫 번째 넘어뜨린 핀 개수 출력")
    @Test
    void getFirstPinTest() {
        Pin pin = miss.getFirstPin();
        assertThat(pin.getFellPins()).isEqualTo(FIRST_POINT);
    }

    @DisplayName("두 번째 넘어뜨린 핀 개수 출력")
    @Test
    void getSecondPinTest() {
        Pin pin = miss.getSecondPin();
        assertThat(pin.getFellPins()).isEqualTo(SECOND_POINT);
    }

    @DisplayName("해당 프레임 종료 여부 출력")
    @Test
    void isFrameEndTest() {
        assertThat(miss.isFrameEnd()).isTrue();
    }

    @DisplayName("추상클래스 명 확인")
    @Test
    void isNameOfStateTest() {
        assertThat(miss.isNameOfState("Miss")).isTrue();
    }

    @DisplayName("추상클래스 명 출력")
    @Test
    void getNameOfStateTest() {
        assertThat(miss.getNameOfState()).isEqualTo("Miss");
    }

    @DisplayName("넘어뜨린 핀 결과 포맷되어 출력")
    @Test
    void getPointTest() {
        assertThat(miss.getPoint()).isEqualTo(String.format("%-4s", FIRST_POINT + "|" + SECOND_POINT));
    }

    @DisplayName("Score 객체 출력")
    @Test
    void getScoreTest() {
        Score score = miss.getScore();
        assertThat(score.getScore()).isEqualTo(FIRST_POINT+SECOND_POINT);
    }
}
