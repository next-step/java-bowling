package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.pins.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreResultTest {

    private ScoreResult scoreResult;
    private Frames frames;

    @BeforeEach
    void setup() {
        scoreResult = ScoreResult.of();
        frames = Frames.of();
    }

    @DisplayName("보너스 프레임 없는 경우 점수 계산 테스트")
    @Test
    void no_bonus_frame() {
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(7));
        frames.bowl(Pins.of(6));
        frames.bowl(Pins.of(2));
        frames.bowl(Pins.of(1));
        frames.bowl(Pins.of(7));
        frames.bowl(Pins.of(6));
        frames.bowl(Pins.of(4));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(7));
        frames.bowl(Pins.of(2));
        frames.bowl(Pins.of(6));
        frames.bowl(Pins.of(7));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(7));
        frames.bowl(Pins.of(2));
        frames.bowl(Pins.of(4));
        frames.bowl(Pins.of(0));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(5));

        List<Frame> frameList = frames.getFrames();
        List<Integer> scores = scoreResult.getScores();
        frameList.forEach(f -> scoreResult.addScoreResult(f.getScoreResult()));

        assertAll(
                () -> assertThat(scores.get(0)).isEqualTo(16),
                () -> assertThat(scores.get(1)).isEqualTo(24),
                () -> assertThat(scores.get(2)).isEqualTo(32),
                () -> assertThat(scores.get(3)).isEqualTo(45),
                () -> assertThat(scores.get(4)).isEqualTo(57),
                () -> assertThat(scores.get(5)).isEqualTo(65),
                () -> assertThat(scores.get(6)).isEqualTo(82),
                () -> assertThat(scores.get(7)).isEqualTo(91),
                () -> assertThat(scores.get(8)).isEqualTo(95),
                () -> assertThat(scores.get(9)).isEqualTo(103)
        );
    }

    @DisplayName("스트라이크로 보너스 프레임 하는 경우 점수 계산 테스트")
    @Test
    void bonus_frame_strike() {
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(8));
        frames.bowl(Pins.of(2));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(5));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(2));
        frames.bowl(Pins.of(6));
        frames.bowl(Pins.of(4));
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(7));

        List<Frame> frameList = frames.getFrames();
        List<Integer> scores = scoreResult.getScores();
        frameList.forEach(f -> scoreResult.addScoreResult(f.getScoreResult()));
        System.out.println("scores = " + scores);

        assertAll(
                () -> assertThat(scores.get(0)).isEqualTo(28),
                () -> assertThat(scores.get(1)).isEqualTo(48),
                () -> assertThat(scores.get(2)).isEqualTo(61),
                () -> assertThat(scores.get(3)).isEqualTo(69),
                () -> assertThat(scores.get(4)).isEqualTo(74),
                () -> assertThat(scores.get(5)).isEqualTo(94),
                () -> assertThat(scores.get(6)).isEqualTo(124),
                () -> assertThat(scores.get(7)).isEqualTo(154),
                () -> assertThat(scores.get(8)).isEqualTo(177),
                () -> assertThat(scores.get(9)).isEqualTo(197)
        );
    }

    @DisplayName("스페어로 보너스 프레임 하는 경우 점수 계산 테스트")
    @Test
    void bonus_frame_spare() {
        frames.bowl(Pins.of(5));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(2));
        frames.bowl(Pins.of(7));
        frames.bowl(Pins.of(9));
        frames.bowl(Pins.of(0));
        frames.bowl(Pins.of(8));
        frames.bowl(Pins.of(2));
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(10));
        frames.bowl(Pins.of(6));
        frames.bowl(Pins.of(4));
        frames.bowl(Pins.of(1));
        frames.bowl(Pins.of(6));
        frames.bowl(Pins.of(3));
        frames.bowl(Pins.of(6));
        frames.bowl(Pins.of(1));
        frames.bowl(Pins.of(9));
        frames.bowl(Pins.of(3));

        List<Frame> frameList = frames.getFrames();
        List<Integer> scores = scoreResult.getScores();
        frameList.forEach(f -> scoreResult.addScoreResult(f.getScoreResult()));

        assertAll(
                () -> assertThat(scores.get(0)).isEqualTo(8),
                () -> assertThat(scores.get(1)).isEqualTo(17),
                () -> assertThat(scores.get(2)).isEqualTo(26),
                () -> assertThat(scores.get(3)).isEqualTo(46),
                () -> assertThat(scores.get(4)).isEqualTo(72),
                () -> assertThat(scores.get(5)).isEqualTo(92),
                () -> assertThat(scores.get(6)).isEqualTo(103),
                () -> assertThat(scores.get(7)).isEqualTo(110),
                () -> assertThat(scores.get(8)).isEqualTo(119),
                () -> assertThat(scores.get(9)).isEqualTo(132)
        );
    }
}