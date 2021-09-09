package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PitchingsTest {

    @Test
    @DisplayName("RegularPitching 생성")
    void create() {
        // given
        Pitching firstPitching = new Pitching(9);

        // when
        Pitchings pitchings = new Pitchings(firstPitching);

        // then
        assertThat(pitchings).isEqualTo(new Pitchings(new Pitching(9)));
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
        Pitchings pitchings = new Pitchings(firstPitching);
        pitchings.pitching(secondPitching);

        // then
        assertThat(pitchings).isEqualTo(new Pitchings(firstPitching, secondPitching));
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
        assertThatThrownBy(() -> new Pitchings(firstPitching, secondPitching))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("score 합계 출력")
    void score() {
        // given
        Pitchings pitchings = new Pitchings(new Pitching(7), new Pitching(2));

        // when
        int score = pitchings.score();

        assertThat(score).isEqualTo(9);

    }

    @Test
    @DisplayName("strike 여부 확인")
    void strike() {
        // given
        Pitchings pitchings1 = new Pitchings(new Pitching(10));
        Pitchings pitchings2 = new Pitchings(new Pitching(9));

        // when
        boolean isStrike1 = pitchings1.strike();
        boolean isStrike2 = pitchings2.strike();

        // then
        assertThat(isStrike1).isTrue();
        assertThat(isStrike2).isFalse();
    }

    @Test
    @DisplayName("spare 여부 확인")
    void spare() {
        // given
        Pitchings pitchings1 = new Pitchings(new Pitching(7), new Pitching(3));
        Pitchings pitchings2 = new Pitchings(new Pitching(7), new Pitching(2));

        // when
        boolean isStrike1 = pitchings1.spare();
        boolean isStrike2 = pitchings2.spare();

        // then
        assertThat(isStrike1).isTrue();
        assertThat(isStrike2).isFalse();
    }

    @Test
    @DisplayName("2번 완료 여부 확인")
    void done() {
        // given
        Pitchings pitchings1 = new Pitchings(new Pitching(7), new Pitching(3));
        Pitchings pitchings2 = new Pitchings(new Pitching(7));

        // when
        boolean isStrike1 = pitchings1.done();
        boolean isStrike2 = pitchings2.done();

        // then
        assertThat(isStrike1).isTrue();
        assertThat(isStrike2).isFalse();
    }
}