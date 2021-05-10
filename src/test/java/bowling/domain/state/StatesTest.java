package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatesTest {

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
        State first = Ready.initialize().bowl(Pins.valueOf(9));
        State second = first.bowl(Pins.valueOf(0));

        // when
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.remove();
        states.add(second);
        boolean actual = states.hasBonusRound();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("States 인스턴스가 보너스 라운드를 얻었는지에 대한 테스트")
    @Test
    void 반환_보너스_여부_있을때() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(0));
        State second = first.bowl(Pins.valueOf(10));

        // when
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.remove();
        states.add(second);
        boolean actual = states.hasBonusRound();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("States 인스턴스에 인스턴스 추가 기능 테스트")
    @Test
    void 추가() {
        // given
        State expected = Strike.initialize();

        // when
        States states = States.initialize();
        states.add(expected);
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
        State expected = Strike.initialize();
        State removed = Ready.initialize().bowl(Pins.valueOf(9));

        // when
        States states = States.initialize();
        states.add(expected);
        states.add(removed);
        states.remove();
        State actual = states.current();

        // then
        assertAll(
                () -> assertThat(actual).isSameAs(expected),
                () -> assertThat(actual).isEqualTo(expected)
        );

    }




    @DisplayName("miss 일 때, States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_miss() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(9));
        State second = first.bowl(Pins.valueOf(0));

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.remove();
        states.add(second);

        // then
        assertAll(
                () -> assertThat(states.score()).isNotNull(),
                () -> assertThat(states.score()).isInstanceOf(Score.class),
                () -> assertThat(states.score().score()).isEqualTo(9)
        );

    }

    @DisplayName("spare and firstBowl 일 때, States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_spare_and_firstBowl() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(9));
        State second = first.bowl(Pins.valueOf(1));
        State third = Ready.initialize().bowl(Pins.valueOf(9));

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.remove();
        states.add(second);
        states.add(third);

        // then
        assertAll(
                () -> assertThat(states.score()).isNotNull(),
                () -> assertThat(states.score()).isInstanceOf(Score.class),
                () -> assertThat(states.score().score()).isEqualTo(19)
        );
    }


    @DisplayName("spare and strike 일 때, States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_spare_and_strike() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(9));
        State second = first.bowl(Pins.valueOf(1));
        State third = Ready.initialize().bowl(Pins.valueOf(10));

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.remove();
        states.add(second);
        states.add(third);

        // then
        assertAll(
                () -> assertThat(states.score()).isNotNull(),
                () -> assertThat(states.score()).isInstanceOf(Score.class),
                () -> assertThat(states.score().score()).isEqualTo(20)
        );
    }


    @DisplayName("strike_and_miss 일때, States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_miss() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(10));
        State second = Ready.initialize().bowl(Pins.valueOf(9));
        State third = second.bowl(Pins.valueOf(0));

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.add(second);
        states.remove();
        states.add(third);

        // then
        assertAll(
                () -> assertThat(states.score()).isNotNull(),
                () -> assertThat(states.score()).isInstanceOf(Score.class),
                () -> assertThat(states.score().score()).isEqualTo(19)
        );

    }

    @DisplayName("strike_and_spare 일때, States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_spare() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(10));
        State second = Ready.initialize().bowl(Pins.valueOf(9));
        State third = second.bowl(Pins.valueOf(1));

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.add(second);
        states.remove();
        states.add(third);

        // then
        assertAll(
                () -> assertThat(states.score()).isNotNull(),
                () -> assertThat(states.score()).isInstanceOf(Score.class),
                () -> assertThat(states.score().score()).isEqualTo(20)
        );

    }

    @DisplayName("strike_and_strike 일때, States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_strike_and_firstBowl() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(10));
        State second = Ready.initialize().bowl(Pins.valueOf(10));
        State third = Ready.initialize().bowl(Pins.valueOf(9));

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.add(second);
        states.add(third);

        // then
        assertAll(
                () -> assertThat(states.score()).isNotNull(),
                () -> assertThat(states.score()).isInstanceOf(Score.class),
                () -> assertThat(states.score().score()).isEqualTo(29)
        );

    }

    @DisplayName("strike_and_strike 일때, States 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_strike_and_strike() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(10));
        State second = Ready.initialize().bowl(Pins.valueOf(10));
        State third = Ready.initialize().bowl(Pins.valueOf(10));

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.add(second);
        states.add(third);

        // then
        assertAll(
                () -> assertThat(states.score()).isNotNull(),
                () -> assertThat(states.score()).isInstanceOf(Score.class),
                () -> assertThat(states.score().score()).isEqualTo(30)
        );

    }

    @DisplayName("beforeScore 가 strike 이고 States 인스턴스가 miss일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_strike_states_miss() {
        // given
        State first = Ready.initialize().bowl(Pins.valueOf(9));
        State second = first.bowl(Pins.valueOf(0));
        Score beforeScoreAsStrike = Strike.initialize().score();

        // when
        // 시나리오대로 작성
        States states = States.initialize();
        states.remove();
        states.add(first);
        states.remove();
        states.add(second);

        // then
        assertAll(
                () -> assertThat(states.calculateAdditionalScore(beforeScoreAsStrike)).isNotNull(),
                () -> assertThat(states.calculateAdditionalScore(beforeScoreAsStrike)).isInstanceOf(Score.class),
                () -> assertThat(states.calculateAdditionalScore(beforeScoreAsStrike).score()).isEqualTo(19)
        );

    }


}