package bowling.domain;

import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StateTest {
    private static State ready;
    private static State firstBowl;
    private static State secondBowl;
    private static State strike;
    private static State spare;
    private static State miss;
    private static State last;

    @BeforeAll
    void setUp() {
        ready = new Ready();
        firstBowl = new FirstBowl(5);
        secondBowl = new SecondBowl(5, 5);
        strike = new Strike();
        spare = new Spare(5, 5);
        miss = new Miss(5, 4);
        last = new Last(5, 5, 5);
    }

    @Test
    @DisplayName("bowl을 수행 했을때 다음 State를 확인한다.")
    void bowl() {
        State ready = new Ready();

        assertThat(ready.bowl(10, false)).isEqualTo(new Strike());
        assertThat(ready.bowl(10, true)).isEqualTo(new FirstBowl(10));
        assertThat(ready.bowl(5, false)).isEqualTo(new FirstBowl(5));

        State firstBowl = new FirstBowl(5);
        assertThat(firstBowl.bowl(5, false)).isEqualTo(new Spare(5, 5));
        assertThat(firstBowl.bowl(5, true)).isEqualTo(new SecondBowl(5, 5));
        assertThat(firstBowl.bowl(4, false)).isEqualTo(new Miss(5, 4));

        State secondBowl = new SecondBowl(5, 4);
        assertThat(secondBowl.bowl(5, true)).isEqualTo(new Last(5, 4, 5));
    }

    @Test
    @DisplayName("State별 getScore 리턴 객체를 확인 한다.")
    void getScore() {
        int totalScore = 0;
        int sumOfMiss = miss.getPins().stream()
                .mapToInt(Pin::getPin)
                .sum();

        int sumOfLast = last.getPins().stream()
                .mapToInt(Pin::getPin)
                .sum();

        assertThat((ready).getScore(totalScore)).isEqualTo(Score.ofNoneScore());
        assertThat((firstBowl).getScore(totalScore)).isEqualTo(Score.ofNoneScore());
        assertThat((secondBowl).getScore(totalScore)).isEqualTo(Score.ofNoneScore());
        assertThat((strike).getScore(totalScore)).isEqualTo(Score.ofStrike(totalScore));
        assertThat((spare).getScore(totalScore)).isEqualTo(Score.ofSpare(totalScore));
        assertThat((miss).getScore(totalScore)).isEqualTo(Score.of(totalScore, sumOfMiss));
        assertThat((last).getScore(totalScore)).isEqualTo(Score.of(totalScore, sumOfLast));
    }

    @ParameterizedTest
    @MethodSource(value = "provideState")
    @DisplayName("State별 addBonus 리턴 객체를 확인 한다.")
    void addBonus(State state, Score score) {
        Score testScore = Score.ofStrike(0);
        assertThat(state.addBonus(testScore)).isEqualTo(score);
    }

    static Stream<Arguments> provideState() {
        return Stream.of(
                Arguments.of(ready, new Score(10, 2)),
                Arguments.of(firstBowl, new Score(15, 1)),
                Arguments.of(secondBowl, new Score(20, 0)),
                Arguments.of(strike, new Score(20, 1)),
                Arguments.of(spare, new Score(20, 0)),
                Arguments.of(miss, new Score(19, 0)),
                Arguments.of(last, new Score(20, 0))
        );
    }

    @Test
    @DisplayName("State별 getPins값을 확인 한다.")
    void getPins() {
        assertThat((ready).getPins()).isEmpty();
        assertThat((firstBowl).getPins()).containsExactly(Pin.of(5));
        assertThat((secondBowl).getPins()).containsExactly(Pin.of(5), Pin.of(5));
        assertThat((strike).getPins()).containsExactly(Pin.ofAllPin());
        assertThat((spare).getPins()).containsExactly(Pin.of(5), Pin.of(5));
        assertThat((miss).getPins()).containsExactly(Pin.of(5), Pin.of(4));
        assertThat((last).getPins()).containsExactly(Pin.of(5), Pin.of(5), Pin.of(5));
    }

    @Test
    @DisplayName("State 별 isEnd값을 확인 한다.")
    void isEnd() {
        assertThat((ready).isEnd()).isFalse();
        assertThat((firstBowl).isEnd()).isFalse();
        assertThat((secondBowl).isEnd()).isFalse();
        assertThat((strike).isEnd()).isTrue();
        assertThat((spare).isEnd()).isTrue();
        assertThat((miss).isEnd()).isTrue();
        assertThat((last).isEnd()).isTrue();
    }
}
