package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstBowl;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatesTest {

    private static final State FIRST_BOWL = State.initialize().bowl(0);
    private static final State MISS = FIRST_BOWL.bowl(9);
    private static final State SPARE = FIRST_BOWL.bowl(10);
    private static final State STRIKE = State.initialize().bowl(10);

    private static final Score MISS_SCORE = Score.miss(Pins.valueOf(9));
    private static final Score SPARE_SCORE = Score.spare();
    private static final Score STRIKE_SCORE = Score.strike();

    @DisplayName("States 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        States states = States.initialize();

        // then
        assertThat(states).isNotNull();
    }

    @DisplayName("States 인스턴스의 현재 값 반환 테스트")
    @Test
    void 반환_current() {
        // when
        States states = States.initialize();

        // then
        assertAll(
                () -> assertThat(states.current()).isNotNull(),
                () -> assertThat(states.current()).isInstanceOf(State.class)
        );

    }

    @DisplayName("States 인스턴스가 보너스 라운드를 못 얻었는지에 대한 테스트")
    @Test
    void 반환_보너스_여부_없을때() {
        // given
        State first = State.initialize().bowl(9);
        State second = first.bowl(0);

        // when
        States states = States.initialize().remove().add(first).remove().add(second);

        // then
        assertThat(states.hasBonusRound()).isFalse();
    }

    @DisplayName("States 인스턴스가 보너스 라운드를 얻었는지에 대한 테스트")
    @Test
    void 반환_보너스_여부_있을때() {
        // given
        State first = State.initialize().bowl(0);
        State second = first.bowl(10);

        // when
        States states = States.initialize().remove().add(first).remove().add(second);

        // then
        assertThat(states.hasBonusRound()).isTrue();
    }

    @DisplayName("States 인스턴스에 인스턴스 추가 기능 테스트")
    @Test
    void 추가() {
        // given
        State expected = new Strike();

        // when
        States states = States.initialize().add(expected);
        State actual = states.current();

        // then
        assertAll(
                () -> assertThat(actual).isSameAs(expected),
                () -> assertThat(actual).isEqualTo(expected)
        );

    }

    @DisplayName("States 인스턴스에 마지막 요소를 삭제하는 기능 테스트")
    @Test
    void 삭제() {
        // given
        State expected = new Strike();
        State removed = State.initialize().bowl(9);

        // when
        States states = States.initialize().add(expected).add(removed).remove();
        State actual = states.current();

        // then
        assertAll(
                () -> assertThat(actual).isSameAs(expected),
                () -> assertThat(actual).isEqualTo(expected)
        );

    }

    @DisplayName("States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score() {
        // when
        // 시나리오대로 작성
        Score firstScore = States.initialize().remove().add(FIRST_BOWL).remove().add(MISS).score();
        Score secondScore = States.initialize().remove().add(FIRST_BOWL).remove().add(SPARE).add(FIRST_BOWL).score();
        Score thirdScore = States.initialize().remove().add(FIRST_BOWL).remove().add(SPARE).add(STRIKE).score();
        Score fourthScore = States.initialize().remove().add(STRIKE).add(FIRST_BOWL).remove().add(MISS).score();
        Score fifthScore = States.initialize().remove().add(STRIKE).add(MISS).remove().add(SPARE).score();
        Score sixthScore = States.initialize().remove().add(STRIKE).add(STRIKE).add(new FirstBowl(9)).score();
        Score seventhScore = States.initialize().remove().add(STRIKE).add(STRIKE).add(STRIKE).score();

        // then
        assertAll(
                () -> assertThat(firstScore.score()).isEqualTo(9),
                () -> assertThat(secondScore.score()).isEqualTo(10),
                () -> assertThat(thirdScore.score()).isEqualTo(20),
                () -> assertThat(fourthScore.score()).isEqualTo(19),
                () -> assertThat(fifthScore.score()).isEqualTo(20),
                () -> assertThat(sixthScore.score()).isEqualTo(29),
                () -> assertThat(seventhScore.score()).isEqualTo(30)
        );

    }


    @DisplayName("States 의 calculateAdditionalScore 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore() {
        // when
        // 시나리오대로 작성
        States missStates = States.initialize().remove().add(FIRST_BOWL).remove().add(MISS);
        States spareStates = States.initialize().remove().add(FIRST_BOWL).remove().add(SPARE).add(STRIKE);
        States strikeStates = States.initialize().remove().add(STRIKE).add(FIRST_BOWL).remove().add(SPARE);
        States allStrikeStates = States.initialize().remove().add(STRIKE).add(STRIKE).add(STRIKE);

        // then
        assertAll(
                () -> assertThat(missStates.calculateAdditionalScore(MISS_SCORE).score()).isEqualTo(9),
                () -> assertThat(missStates.calculateAdditionalScore(SPARE_SCORE).score()).isEqualTo(10),
                () -> assertThat(missStates.calculateAdditionalScore(STRIKE_SCORE).score()).isEqualTo(19),
                () -> assertThat(spareStates.calculateAdditionalScore(MISS_SCORE).score()).isEqualTo(9),
                () -> assertThat(spareStates.calculateAdditionalScore(SPARE_SCORE).score()).isEqualTo(10),
                () -> assertThat(spareStates.calculateAdditionalScore(STRIKE_SCORE).score()).isEqualTo(20),
                () -> assertThat(strikeStates.calculateAdditionalScore(MISS_SCORE).score()).isEqualTo(9),
                () -> assertThat(strikeStates.calculateAdditionalScore(SPARE_SCORE).score()).isEqualTo(20),
                () -> assertThat(strikeStates.calculateAdditionalScore(STRIKE_SCORE).score()).isEqualTo(20),
                () -> assertThat(allStrikeStates.calculateAdditionalScore(MISS_SCORE).score()).isEqualTo(9),
                () -> assertThat(allStrikeStates.calculateAdditionalScore(SPARE_SCORE).score()).isEqualTo(20),
                () -> assertThat(allStrikeStates.calculateAdditionalScore(STRIKE_SCORE).score()).isEqualTo(30)
        );

    }

    @DisplayName("States 의 description 기능 테스트")
    @Test
    void 반환_description() {
        // given
        State firstBowlOne = new FirstBowl(1);
        State missEight = firstBowlOne.bowl(8);
        State firstBowlNine = new FirstBowl(9);
        State missZero = firstBowlNine.bowl(0);
        State spareNine = firstBowlOne.bowl(9);

        // when
        // 시나리오대로 작성
        States readyStates = States.initialize();
        States firstBowlGutterStates = States.initialize().remove().add(FIRST_BOWL);
        States firstBowlGenericStates = States.initialize().remove().add(new FirstBowl(9));
        States strikeOnceStates = States.initialize().remove().add(STRIKE);
        States missGenericStates = States.initialize().remove().add(firstBowlOne).remove().add(missEight);
        States missGutterFirstStates = States.initialize().remove().add(FIRST_BOWL).remove().add(MISS);
        States missGutterLastStates = States.initialize().remove().add(firstBowlNine).remove().add(missZero);
        States spareGenericStates = States.initialize().remove().add(firstBowlOne).remove().add(spareNine).add(STRIKE);
        States spareGutterStates = States.initialize().remove().add(FIRST_BOWL).remove().add(SPARE).add(STRIKE);
        States strikeGutterStates = States.initialize().remove().add(STRIKE).add(State.initialize().bowl(Pins.valueOf(0))).remove().add(SPARE);
        States strikeLastGutterStates = States.initialize().remove().add(STRIKE).add(STRIKE).add(FIRST_BOWL);
        States allStrikeStates = States.initialize().remove().add(STRIKE).add(STRIKE).add(STRIKE);

        // then
        assertAll(
                () -> assertThat(readyStates.description()).isEqualTo(Strings.EMPTY),
                () -> assertThat(firstBowlGutterStates.description()).isEqualTo("-"),
                () -> assertThat(firstBowlGenericStates.description()).isEqualTo("9"),
                () -> assertThat(strikeOnceStates.description()).isEqualTo("X"),
                () -> assertThat(missGenericStates.description()).isEqualTo("1|8"),
                () -> assertThat(missGutterFirstStates.description()).isEqualTo("-|9"),
                () -> assertThat(missGutterLastStates.description()).isEqualTo("9|-"),
                () -> assertThat(spareGenericStates.description()).isEqualTo("1|/|X"),
                () -> assertThat(spareGutterStates.description()).isEqualTo("-|/|X"),
                () -> assertThat(strikeGutterStates.description()).isEqualTo("X|-|/"),
                () -> assertThat(strikeLastGutterStates.description()).isEqualTo("X|X|-"),
                () -> assertThat(allStrikeStates.description()).isEqualTo("X|X|X")
        );

    }

}