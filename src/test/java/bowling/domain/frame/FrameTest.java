package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.bowl.NextBowl;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static bowling.domain.score.ScoreTest.score;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    static final Bowl FIRST_BOWL = FirstBowl.bowl();

    @DisplayName("프레임 번호가 1 ~ 10 이 아니라면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] number: {0}")
    @ValueSource(ints = {-1, 0, 11})
    void create_illegalNumber(int number) {
        assertThatThrownBy(() -> new Frame(number, FIRST_BOWL))
                .isInstanceOf(IllegalFrameNumberException.class);
    }

    @DisplayName("현재 프레임이 종료되지 않았는데 다음프레임을 생성하면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] bowl: {0}")
    @MethodSource("nextArguments")
    void next(Bowl bowl) {
        Frame frame = new Frame(bowl);
        assertThatThrownBy(() -> frame.nextOf(FIRST_BOWL))
                .isInstanceOf(UnFinishedFrameException.class);
    }

    public static Stream<Arguments> nextArguments() {
        return Stream.of(
                Arguments.of(FIRST_BOWL),
                Arguments.of(new NextBowl(5))
        );
    }

    @DisplayName("미스일 때 점수가 잘 더해지는지")
    @Test
    void pitchMiss_addScore() {
        //given
        Score score = Score.base();
        Frame frame = new Frame(score, FIRST_BOWL);

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
        Frame frame = new Frame(score, FIRST_BOWL);

        //when
        frame.pitch(Pin.from(10));

        //then
        assertThat(score).isEqualTo(score(10, 2));
    }

}
