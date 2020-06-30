package bowling.domain.pitch;

import bowling.domain.exception.NormalPitchTryException;
import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalPitchesTest {

    @DisplayName("첫 투구면 Pitch를 initiate를 통해 생성하여 내부 컬렉션에 추가함")
    @Test
    public void throwBall_첫_투구() {
        PitchScore pitchScore = PitchScore.valueOf(5);
        NormalPitches normalPitches = new NormalPitches();

        normalPitches.throwBall(pitchScore);

        assertThat(normalPitches.getPitchScoreSignatures().get(0)).isEqualTo("5");
    }

    @DisplayName("두 번째 투구면 Pitch를 next를 통해 생성하여 내부 컬렉션에 추가하며, 스페어임")
    @Test
    public void throwBall_두번째_투구_스페어() {
        NormalPitches normalPitches = new NormalPitches();

        normalPitches.throwBall(PitchScore.valueOf(5));
        normalPitches.throwBall(PitchScore.valueOf(5));

        assertThat(normalPitches.getPitchScoreSignatures().get(1)).isEqualTo("/");
    }

    @DisplayName("Normal Frame의 Normal Pitches는 2번 초과 투구시 예외 발생")
    @Test
    public void throwBall_세번째_예외() {
        NormalPitches normalPitches = new NormalPitches();
        normalPitches.throwBall(PitchScore.valueOf(0));
        normalPitches.throwBall(PitchScore.valueOf(3));

        assertThatThrownBy(() -> {
            normalPitches.throwBall(PitchScore.valueOf(3));
        }).isInstanceOf(NormalPitchTryException.class);
    }

    @DisplayName("Score들의 시그니쳐 List를 요청")
    @Test
    public void getScores() {
        NormalPitches normalPitches = new NormalPitches();
        normalPitches.throwBall(PitchScore.valueOf(0));
        normalPitches.throwBall(PitchScore.valueOf(3));

        List<String> scores = normalPitches.getPitchScoreSignatures();

        assertThat(scores).containsExactly("-", "3");
    }

    @DisplayName("스트라이크인 경우 isSpare 테스트 (Index 예외 발생하지 않음)")
    @Test
    public void isSpare_False() {
        NormalPitches normalPitches = new NormalPitches();
        normalPitches.throwBall(PitchScore.valueOf(10));

        assertThat(normalPitches.hasSpare()).isFalse();
    }
}
