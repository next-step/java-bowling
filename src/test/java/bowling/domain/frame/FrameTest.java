package bowling.domain.frame;

import bowling.domain.bowl.FirstBowl;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.score.ScoreTest.score;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    @DisplayName("프레임 번호가 1 ~ 10 이 아니라면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] number: {0}")
    @ValueSource(ints = {-1, 0, 11})
    void create_illegalNumber(int number) {
        FirstBowl bowl = new FirstBowl();
        assertThatThrownBy(() -> new Frame(number, bowl))
                .isInstanceOf(IllegalFrameNumberException.class);
    }

    @DisplayName("미스일 때 점수가 잘 더해지는지")
    @Test
    void pitchMiss_addScore() {
        //given
        Score score = Score.base();
        Frame frame = new Frame(score, new FirstBowl());

        //when
        frame.pitch(Pin.from(5));
        frame.pitch(Pin.from(1));

        //then
        assertThat(score).isEqualTo(score(6));
    }

    @DisplayName("스트라이크일 때 점수와 보너스 기회가 잘 더해지는지")
    @Test
    void pitchStrike_addScore() {
        //given
        Score score = Score.base();
        Frame frame = new Frame(score, new FirstBowl());

        //when
        frame.pitch(Pin.from(10));

        //then
        assertThat(score).isEqualTo(score(10, 2));
    }

}
