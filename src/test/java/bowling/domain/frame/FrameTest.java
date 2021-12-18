package bowling.domain.frame;

import bowling.domain.bowl.*;
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

    @DisplayName("프레임 번호가 1 ~ 10 이 아니라면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] number: {0}")
    @ValueSource(ints = {-1, 0, 11})
    void create_illegalNumber(int number) {
        FirstBowl bowl = firstBowl();
        assertThatThrownBy(() -> new Frame(number, bowl))
                .isInstanceOf(IllegalFrameNumberException.class);
    }

    @DisplayName("현재 프레임이 종료되지 않았는데 다음프레임을 생성하면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] bowl: {0}")
    @MethodSource("nextArguments")
    void next(Bowl bowl) {
        Frame frame = new Frame(bowl);
        FirstBowl nextBowl = firstBowl();

        assertThatThrownBy(() -> frame.nextOf(nextBowl))
                .isInstanceOf(UnFinishedFrameException.class);
    }

    public static Stream<Arguments> nextArguments() {
        return Stream.of(
                Arguments.of(firstBowl()),
                Arguments.of(new NextBowl(5))
        );
    }

    @DisplayName("미스일 때 점수가 잘 더해지는지")
    @Test
    void pitchMiss_addScore() {
        //given
        Score score = Score.base();
        Frame frame = new Frame(score, firstBowl());

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
        Frame frame = new Frame(score, firstBowl());

        //when
        frame.pitch(Pin.from(10));

        //then
        assertThat(score).isEqualTo(score(10, 2));
    }

    @DisplayName("이전 프레임에 점수를 더하고 더해진 점수만큼 현재 프레임의 점수에도 반영한다.")
    @Test
    void calculateScoreOfPreviousFrame() {
        //given
        Score previousScore = Score.strike();
        Frame previousFrame = new Frame(previousScore, new StrikeBowl());

        Score currentScore = previousScore.next();
        Frame currentFrame = new Frame(currentScore, firstBowl());

        //when
        currentFrame.calculateScoreOfPreviousFrame(score(5), previousFrame);

        //then
        assertThat(previousScore).isEqualTo(score(15, 1));
        assertThat(currentScore).isEqualTo(score(15, 0));
    }

    @DisplayName("이전 프레임에 보너스 기회가 없다면 현재 프레임 점수는 변경이 없다.")
    @Test
    void calculateScoreOfPreviousFrame_previousFrameWithNoBonusChance() {
        //given
        Score previousScore = score(9, 0);
        Frame previousFrame = new Frame(previousScore, new MissBowl(4, 5));

        Score currentScore = previousScore.next();
        Frame currentFrame = new Frame(currentScore, firstBowl());

        //when
        currentFrame.calculateScoreOfPreviousFrame(score(5), previousFrame);

        //then
        assertThat(previousScore).isEqualTo(score(9, 0));
        assertThat(currentScore).isEqualTo(score(9, 0));
    }

    private static FirstBowl firstBowl() {
        return new FirstBowl();
    }

}
