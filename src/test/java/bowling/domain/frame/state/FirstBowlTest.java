package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.Score;

class FirstBowlTest {
    @Test
    @DisplayName("두번째 시도에서 모든 핀을 쓰러트린다면 스페어 상태를 반환한다.")
    void bowlWhenSpare() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        assertThat(firstBowl.bowl(6) instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("두번째 시도에서 모든 핀을 쓰러뜨리지 못하면 Miss 상태를 반환한다.")
    void bowlWhenMiss() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        assertThat(firstBowl.bowl(5) instanceof Miss).isTrue();
    }

    @Test
    @DisplayName("해당 상태에서는 해당 프레임이 진행중임을 알린다.")
    void isFinish() {
        assertThat(new FirstBowl(new Pins(4)).isFinish()).isFalse();
    }

    @Test
    @DisplayName("쓰러뜨린 핀의 합계가 10이 넘으면 예외를 던진다.")
    void bowlWhenOver10Pins() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        assertThatThrownBy(() -> firstBowl.bowl(7))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("쓰러트린 핀의 합계는 10을 넘을 수 없습니다");
    }

    @Test
    @DisplayName("Score 객체를 요구할 시에, 계산 불가한 Score 객체를 반환한다.")
    void getScore() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        Score score = firstBowl.getScore();
        assertThat(score).isEqualTo(Score.needToMoreBowl());
        assertThat(score.canCalculateScore()).isFalse();
    }

    @ParameterizedTest(name = "주어진 스코어에 따라 보너스 점수를 계산해주어야 한다; {1}")
    @MethodSource("provideScoreSource")
    void calculateBonusScore(FirstBowl firstBowl, Score score, Score expected) {
        assertThat(firstBowl.calculateBonusScore(score)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideScoreSource() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        return Stream.of(
            Arguments.of(firstBowl, new Score(10, 2), new Score(14, 1)),
            Arguments.of(firstBowl, new Score(10, 1), new Score(14, 0))
        );
    }
}
