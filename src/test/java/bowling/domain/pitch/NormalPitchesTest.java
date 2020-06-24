package bowling.domain.pitch;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalPitchesTest {

    @DisplayName("첫 투구면 Pitch를 initiate를 통해 생성하여 내부 컬렉션에 추가함")
    @Test
    public void throwBall_첫_투구() {
        Score score = Score.valueOf(5);
        NormalPitches normalPitches = new NormalPitches();

        normalPitches.throwBall(score);

        assertThat(normalPitches.getScoreSignatures().get(0)).isEqualTo("5");
    }

    @DisplayName("두 번째 투구면 Pitch를 next를 통해 생성하여 내부 컬렉션에 추가하며, 스페어임")
    @Test
    public void throwBall_두번째_투구() {
        Score score = Score.valueOf(5);
        NormalPitches normalPitches = new NormalPitches();

        normalPitches.throwBall(score);
        normalPitches.throwBall(Score.valueOf(5));

        assertThat(normalPitches.getScoreSignatures().get(1)).isEqualTo("/");
    }

    @DisplayName("Normal Frame의 Normal Pitches는 2번 초과 투구시 예외 발생")
    @Test
    public void throwBall_세번째_예외() {
        NormalPitches normalPitches = new NormalPitches();
        normalPitches.throwBall(Score.valueOf(0));
        normalPitches.throwBall(Score.valueOf(3));

        assertThatThrownBy(() -> {
            normalPitches.throwBall(Score.valueOf(3));
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_NORMAL_PITCHES_TRY);
    }

    @DisplayName("Score들의 List를 요청")
    @Test
    public void getScores() {
        NormalPitches normalPitches = new NormalPitches();
        normalPitches.throwBall(Score.valueOf(0));
        normalPitches.throwBall(Score.valueOf(3));

        List<String> scores = normalPitches.getScoreSignatures();

        assertThat(scores).containsExactly("-", "3");
    }

    @DisplayName("스트라이크인 경우 isSpare 테스트 (Index 예외 발생하지 않음)")
    @Test
    public void isSpare_False() {
        NormalPitches normalPitches = new NormalPitches();
        normalPitches.throwBall(Score.valueOf(10));

        assertThat(normalPitches.isSpare()).isFalse();
    }
}
