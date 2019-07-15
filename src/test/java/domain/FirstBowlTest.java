package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlTest {
    private final int FIRST_POINT = 2;

    private FirstBowl firstBowl;

    @BeforeEach
    void setUp() {
        firstBowl = new FirstBowl(FIRST_POINT);
    }

    @DisplayName("프레임 점수 포맷된 출력")
    @Test
    void getPointsTest() {
        assertThat(firstBowl.getPoints()).isEqualTo(FIRST_POINT);
    }

    @DisplayName("첫 번째 넘어뜨린 핀 개수 출력")
    @Test
    void getFirstPinTest() {
        Pin pin = firstBowl.getFirstPin();
        assertThat(pin.getFellPins()).isEqualTo(FIRST_POINT);
    }

    @DisplayName("해당 프레임 종료 여부 출력")
    @Test
    void isFrameEndTest() {
        assertThat(firstBowl.isFrameEnd()).isFalse();
    }

    @DisplayName("추상클래스 명 확인")
    @Test
    void isNameOfStateTest() {
        assertThat(firstBowl.isNameOfState("FirstBowl")).isTrue();
    }

    @DisplayName("추상클래스 명 출력")
    @Test
    void getNameOfStateTest() {
        assertThat(firstBowl.getNameOfState()).isEqualTo("FirstBowl");
    }

    @DisplayName("넘어뜨린 핀 결과 포맷되어 출력")
    @Test
    void getPointTest() {
        assertThat(firstBowl.getPoint()).isEqualTo(String.format("%-4s", FIRST_POINT));
    }

    @DisplayName("Score 객체 출력")
    @Test
    void getScoreTest() {
        Score score = firstBowl.getScore();
        assertThat(score.getScore()).isEqualTo(FIRST_POINT);
    }
}
