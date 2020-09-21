package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = FinalScores.newInstance();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 10점 (스트라이크)이면 해당 프레임에서 추가로 던질 수 있음.")
    void canFinalPitchIsStrike() {
        scores.add(Score.from("10"));
        assertThat(scores.canPitching()).isTrue();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 10점 (스트라이크)이고 다음 두번째 점수도 10점 (스트라이크)일 경우 추가로 던질 수 있음.")
    void canFinalPitchIsDouble() {
        scores.add(Score.from("10"));
        scores.add(Score.from("10"));
        assertThat(scores.canPitching()).isTrue();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 해당 프레임에서 추가로 던질 수 있음.")
    void canFinalPitchIsSpare() {
        scores.add(Score.from("4"));
        scores.add(Score.from("6"));
        assertThat(scores.canPitching()).isTrue();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 추가로 던질 수 있음. 다음 점수가 10점(스페어)일 경우에 추가로 던질 수 없음.")
    void canFinalPitchIsSpareBonusPitchingIsTen() {
        scores.add(Score.from("4"));
        scores.add(Score.from("6"));
        scores.add(Score.from("10"));
        assertThat(scores.canPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 추가로 던질 수 있음. 다음 점수가 있을 경우에 추가로 던질 수 없음.")
    void canFinalPitchIsSpareBonusPitching() {
        scores.add(Score.from("4"));
        scores.add(Score.from("6"));
        scores.add(Score.from("8"));
        assertThat(scores.canPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 미만이면 해당 프레임에서 추가로 던질 수 없음.")
    void canFinalPitchIsClose() {
        scores.add(Score.from("4"));
        scores.add(Score.from("4"));
        assertThat(scores.canPitching()).isFalse();
    }

    @Test
    @DisplayName("파이널 프레임의 경우 다음 횟수 여부 확인. 두 횟수 모두 0점(거터)이면 해당 프레임에서 추가로 던질 수 없음.")
    void canFinalPitchIsGutter() {
        scores.add(Score.from("0"));
        scores.add(Score.from("0"));
        assertThat(scores.canPitching()).isFalse();
    }

}
