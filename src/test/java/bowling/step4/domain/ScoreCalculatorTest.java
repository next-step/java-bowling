package bowling.step4.domain;

import bowling.step4.domain.frame.Frames;
import bowling.step4.dto.ScoreDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ScoreCalculatorTest {
    @Test
    void 세번_스트라이크_점수_계산() {
        Frames frames = new Frames();
        frames.bowl(1, 10);
        frames.bowl(2, 10);
        frames.bowl(3, 10);
        ScoreCalculator scoreCalculator = new ScoreCalculator(frames);
        assertThat(scoreCalculator.calculate().get(0).score).isEqualTo(30);
    }

    @Test
    void 스페어후_첫번째스코어_생겼을때_점수_계산() {
        Frames frames = new Frames();
        frames.bowl(1, 10);
        frames.bowl(2, 2);
        frames.bowl(2, 8);
        frames.bowl(3, 2);
        ScoreCalculator scoreCalculator = new ScoreCalculator(frames);
        List<ScoreDto> scoreDtos = scoreCalculator.calculate();
        assertSoftly(softly -> {
            assertThat(scoreDtos.get(0).score).isEqualTo(20);
            assertThat(scoreDtos.get(1).score).isEqualTo(32);
        });
    }

    @Test
    void 스코어후_다음판도종료됐을때_첫번째값만_계산() {
        Frames frames = new Frames();
        frames.bowl(1, 9);
        frames.bowl(1, 0);
        frames.bowl(2, 2);
        frames.bowl(2, 8);
        frames.bowl(3, 2);
        frames.bowl(3, 4);
        ScoreCalculator scoreCalculator = new ScoreCalculator(frames);
        List<ScoreDto> scoreDtos = scoreCalculator.calculate();
        assertSoftly(softly -> {
            assertThat(scoreDtos.get(0).score).isEqualTo(9);
            assertThat(scoreDtos.get(1).score).isEqualTo(21);
            assertThat(scoreDtos.get(2).score).isEqualTo(27);
        });
    }

    @Test
    void 두번스트라이크_후_미스() {
        Frames frames = new Frames();
        frames.bowl(1, 10);
        frames.bowl(2, 10);
        frames.bowl(3, 2);
        frames.bowl(3, 6);
        ScoreCalculator scoreCalculator = new ScoreCalculator(frames);
        List<ScoreDto> scoreDtos = scoreCalculator.calculate();
        assertSoftly(softly -> {
            assertThat(scoreDtos.get(0).score).isEqualTo(20);
            assertThat(scoreDtos.get(1).score).isEqualTo(38);
            assertThat(scoreDtos.get(2).score).isEqualTo(46);
        });
    }
}