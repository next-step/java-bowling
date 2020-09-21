package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = NormalScores.newInstance();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 10점 (스트라이크)이면 해당 프레임에서 추가로 던질 수 없음.")
    void canNormalPitchIsStrike() {
        scores.add(Score.from("10"));
        assertThat(scores.canPitching()).isFalse();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 0점 (거터)이면 해당 프레임에서 추가로 던질 수 있음.")
    void canNormalPitchIsGutter() {
        scores.add(Score.from("0"));
        assertThat(scores.canPitching()).isTrue();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 첫번째 점수가 1 ~ 9점이면 해당 프레임에서 추가로 던질 수 있음.")
    void canNormalPitchIsMiss() {
        scores.add(Score.from("4"));
        assertThat(scores.canPitching()).isTrue();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 10점 (스페어)이면 해당 프레임에서 추가로 던질 수 없음.")
    void canNormalPitchIsSpare() {
        scores.add(Score.from("7"));
        scores.add(Score.from("3"));
        assertThat(scores.canPitching()).isFalse();
    }

    @Test
    @DisplayName("노말 프레임의 경우 다음 횟수 여부 확인. 두번째 점수가 있을 경우 추가로 던질 수 없음.")
    void canNormalPitchIsClose() {
        scores.add(Score.from("3"));
        scores.add(Score.from("3"));
        assertThat(scores.canPitching()).isFalse();
    }

}
