package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    protected final int FIRST_POINT = 2;
    protected final int SPARE_POINT = 8;

    private Spare spare;

    @BeforeEach
    void setUp() {
        spare = new Spare(Pin.of(FIRST_POINT), Pin.of(SPARE_POINT));
    }

    @DisplayName("프레임 점수 포맷된 출력")
    @Test
    void getPointsTest() {
        assertThat(spare.getPoints()).isEqualTo(FIRST_POINT + SPARE_POINT);
    }

    @DisplayName("첫 번째 넘어뜨린 핀 개수 출력")
    @Test
    void getFirstPinTest() {
        Pin pin = spare.getFirstPin();
        assertThat(pin.getFellPins()).isEqualTo(FIRST_POINT);
    }

    @DisplayName("두 번째 넘어뜨린 핀 개수 출력")
    @Test
    void getSecondPinTest() {
        Pin pin = spare.getSecondPin();
        assertThat(pin.getFellPins()).isEqualTo(SPARE_POINT);
    }

    @DisplayName("해당 프레임 종료 여부 출력")
    @Test
    void isFrameEndTest() {
        assertThat(spare.isFrameEnd()).isTrue();
    }

    @DisplayName("추상클래스 명 확인")
    @Test
    void isNameOfStateTest() {
        assertThat(spare.isNameOfState("Spare")).isTrue();
    }

    @DisplayName("추상클래스 명 출력")
    @Test
    void getNameOfStateTest() {
        assertThat(spare.getNameOfState()).isEqualTo("Spare");
    }

    @DisplayName("넘어뜨린 핀 결과 포맷되어 출력")
    @Test
    void getPointTest() {
        String pointSymbol = PointName.valueOfPointName(FIRST_POINT + SPARE_POINT, true);
        assertThat(spare.getPoint()).isEqualTo(String.format("%-4s", FIRST_POINT + "|" + pointSymbol));
    }

    @DisplayName("Score 객체 출력")
    @Test
    void getScoreTest() {
        Score score = spare.getScore();
        assertThat(score.getScore()).isEqualTo(FIRST_POINT + SPARE_POINT);
    }
}
