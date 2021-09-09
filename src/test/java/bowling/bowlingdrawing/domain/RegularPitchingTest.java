package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RegularPitchingTest {

    @Test
    @DisplayName("RegularPitching 생성")
    void create() {
        // given
        Pitching firstPitching = new Pitching(9);

        // when
        RegularPitching regularPitching = new RegularPitching(firstPitching);

        // then
        assertThat(regularPitching).isEqualTo(new RegularPitching(new Pitching(9)));
    }

    @Test
    @DisplayName("SecondPitching 추가")
    void create_and_add_second_pitching() {
        // given
        int pinsFirst = 7;
        int pinsSecond = 2;
        Pitching firstPitching = new Pitching(pinsFirst);
        Pitching secondPitching = new Pitching(pinsSecond);

        // when
        RegularPitching regularPitching = new RegularPitching(firstPitching);
        regularPitching.secondPitch(secondPitching);

        // then
        assertThat(regularPitching).isEqualTo(new RegularPitching(firstPitching, secondPitching));
    }

    @Test
    @DisplayName("검증 fail: 합계 score 10 초과")
    void create_fail() {
        // given
        int pinsFirst = 9;
        int pinsSecond = 2;

        // when
        Pitching firstPitching = new Pitching(pinsFirst);
        Pitching secondPitching = new Pitching(pinsSecond);

        // then
        assertThatThrownBy(() -> new RegularPitching(firstPitching, secondPitching))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("score 합계 출력")
    void score() {
        // given
        RegularPitching regularPitching = new RegularPitching(new Pitching(7), new Pitching(2));

        // when
        int score = regularPitching.score();

        assertThat(score).isEqualTo(9);

    }

    @Test
    @DisplayName("strike 여부 확인")
    void strike() {
        // given
        RegularPitching regularPitching1 = new RegularPitching(new Pitching(10));
        RegularPitching regularPitching2 = new RegularPitching(new Pitching(9));

        // when
        boolean isStrike1 = regularPitching1.strike();
        boolean isStrike2 = regularPitching2.strike();

        // then
        assertThat(isStrike1).isTrue();
        assertThat(isStrike2).isFalse();
    }

    @Test
    @DisplayName("spare 여부 확인")
    void spare() {
        // given
        RegularPitching regularPitching1 = new RegularPitching(new Pitching(7), new Pitching(3));
        RegularPitching regularPitching2 = new RegularPitching(new Pitching(7), new Pitching(2));

        // when
        boolean isStrike1 = regularPitching1.spare();
        boolean isStrike2 = regularPitching2.spare();

        // then
        assertThat(isStrike1).isTrue();
        assertThat(isStrike2).isFalse();
    }

    @Test
    @DisplayName("2번 완료 여부 확인")
    void done() {
        // given
        RegularPitching regularPitching1 = new RegularPitching(new Pitching(7), new Pitching(3));
        RegularPitching regularPitching2 = new RegularPitching(new Pitching(7));

        // when
        boolean isStrike1 = regularPitching1.done();
        boolean isStrike2 = regularPitching2.done();

        // then
        assertThat(isStrike1).isTrue();
        assertThat(isStrike2).isFalse();
    }
}