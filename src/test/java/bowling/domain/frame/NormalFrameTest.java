package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 프레임을 생성할 수 있다.")
    void createFirstFrameTest() {

        // given
        Frame expected = NormalFrame.of(1, NormalScore.emptyScore(), null);

        // when
        Frame result = NormalFrame.createFirstFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 Frame을 생성할 수 있다.")
    void createNextFrameTest() {

        // given
        Frame input = NormalFrame.of(2, NormalScore.emptyScore(), null);

        Frame expected = NormalFrame.of(3, NormalScore.emptyScore(), null);

        // when
        Frame result = input.createNextFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 Frame을 반환받을 수 있다.")
    void nextFrameTest() {

        // given
        Frame expected = NormalFrame.of(3, NormalScore.emptyScore(), null);
        Frame input = NormalFrame.of(2, NormalScore.emptyScore(), expected);

        // when
        Frame result = input.nextFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("현재 score를 반환할 수 있다.")
    void scoreTest() {

        // given
        Score score = NormalScore.emptyScore();
        Frame input = NormalFrame.of(2, score, null);

        // when
        Score result = input.score();

        // then
        assertThat(result).isEqualTo(score);
    }

    @Test
    @DisplayName("score 추가 저장 유무를 반환할 수 있다.")
    void isScoreNextStorableTest() {

        // given
        Score score = NormalScore.emptyScore();
        Frame input = NormalFrame.of(2, score, null);

        // when
        boolean result = input.isScoreNextStorable();

        // then
        assertTrue(result);
    }

}