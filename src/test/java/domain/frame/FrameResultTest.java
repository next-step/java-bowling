package domain.frame;

import domain.Pins;
import domain.Score;
import domain.state.closed.Strike;
import domain.state.open.Hit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @Test
    void 점수_계산이_끝나지_않은_경우_UNFINISHED_SCORE를_반환한다() {
        //given
        Score score = Score.of(10, 2);

        //when
        FrameResult frameResult = FrameResult.of(new Strike(), score);

        //then
        assertThat(frameResult.getScore()).isEqualTo(FrameResult.UNFINISHED_SCORE);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 11, 20, 30})
    void 점수_계산이_끝난_경우_최종점수를_반환한다(int finalScore) {
        //given
        Score score = Score.of(finalScore, 0);

        //when
        FrameResult frameResult = FrameResult.of(new Strike(), score);

        //then
        assertThat(frameResult.getScore()).isEqualTo(finalScore);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    void 프레임이_Closed_되지_않았을_경우_UNFINISHED_SCORE를_반환한다(int firstPins) {
        //given
        Score score = Score.of(firstPins, 0);

        //when
        FrameResult frameResult = FrameResult.of(new Hit(Pins.from(firstPins)), score);

        //then
        assertThat(frameResult.getScore()).isEqualTo(FrameResult.UNFINISHED_SCORE);
    }
}
