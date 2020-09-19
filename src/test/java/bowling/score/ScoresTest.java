package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = Scores.newInstance();
    }

    @Test
    @DisplayName("2개의 입력값이 10일 경우 Spare")
    void isSpare() {
        scores.add(Score.from("7"));
        scores.add(Score.from("3"));
        assertThat(scores.isSpare()).isTrue();
    }

    @Test
    @DisplayName("남은 Pin 검증")
    void getRemainingPins() {
        scores.add(Score.from("7"));
        assertThat(scores.getRemainingPins()).isEqualTo(3);
    }

    @Test
    @DisplayName("Scores의 합이 10이 넘을 경우 Exception 발생")
    void sumAllMaxIsTenException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    scores.add(Score.from("7"));
                    scores.add(Score.from("4"));
                    scores.getScores();
                });
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 10점 (스트라이크)이면 해당 프레임에서 추가로 던질 수 없음.")
    void canNormalPitchIsStrike() {
        scores.add(Score.from("10"));
        assertThat(scores.canNormalPitching()).isFalse();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 0점 (거터)이면 해당 프레임에서 추가로 던질 수 있음.")
    void canNormalPitchIsGutter() {
        scores.add(Score.from("0"));
        assertThat(scores.canNormalPitching()).isTrue();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 1 ~ 9점이면 해당 프레임에서 추가로 던질 수 있음.")
    void canNormalPitchIsMiss() {
        scores.add(Score.from("4"));
        assertThat(scores.canNormalPitching()).isTrue();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 해당 프레임에서 추가로 던질 수 없음.")
    void canNormalPitchIsSpare() {
        scores.add(Score.from("7"));
        scores.add(Score.from("3"));
        assertThat(scores.canNormalPitching()).isFalse();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 있을 경우 추가로 던질 수 없음.")
    void canNormalPitchIsClose() {
        scores.add(Score.from("3"));
        scores.add(Score.from("3"));
        assertThat(scores.canNormalPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 10점 (스트라이크)이면 해당 프레임에서 추가로 던질 수 있음.")
    void canFinalPitchIsStrike() {
        scores.add(Score.from("10"));
        assertThat(scores.canFinalPitching()).isTrue();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 10점 (스트라이크)이고 다음 두번째 점수도 10점 (스트라이크)일 경우 추가로 던질 수 없음.")
    void canFinalPitchIsDouble() {
        scores.add(Score.from("10"));
        scores.add(Score.from("10"));
        assertThat(scores.canFinalPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 해당 프레임에서 추가로 던질 수 있음.")
    void canFinalPitchIsSpare() {
        scores.add(Score.from("4"));
        scores.add(Score.from("6"));
        assertThat(scores.canFinalPitching()).isTrue();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 추가로 던질 수 있음. 다음 점수가 10점(스페어)일 경우에 추가로 던질 수 없음.")
    void canFinalPitchIsSpareBonusPitchingIsTen() {
        scores.add(Score.from("4"));
        scores.add(Score.from("6"));
        scores.add(Score.from("10"));
        assertThat(scores.canFinalPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 추가로 던질 수 있음. 다음 점수가 있을 경우에 추가로 던질 수 없음.")
    void canFinalPitchIsSpareBonusPitching() {
        scores.add(Score.from("4"));
        scores.add(Score.from("6"));
        scores.add(Score.from("8"));
        assertThat(scores.canFinalPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 미만이면 해당 프레임에서 추가로 던질 수 없음.")
    void canFinalPitchIsClose() {
        scores.add(Score.from("4"));
        scores.add(Score.from("4"));
        assertThat(scores.canFinalPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두 횟수 모두 0점(거터)이면 해당 프레임에서 추가로 던질 수 없음.")
    void canFinalPitchIsGutter() {
        scores.add(Score.from("0"));
        scores.add(Score.from("0"));
        assertThat(scores.canFinalPitching()).isFalse();
    }
}
