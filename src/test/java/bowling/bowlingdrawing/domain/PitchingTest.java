package bowling.bowlingdrawing.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PitchingTest {

    @ParameterizedTest(name = "first Pitching 생성")
    @ValueSource(ints = {0, 6, 10}) // given
    void create_first(int pins) {
        // when
        Pitching pitching = Pitching.first(pins);
        // then
        assertThat(pitching).isEqualTo(Pitching.of(pins));
    }

    @ParameterizedTest(name = "next Pitching 생성")
    @ValueSource(ints = {0, 6, 10}) // given
    void create_next(int pins) {
        // given
        Pitching pitching = Pitching.first(pins);
        // when
        Pitching nextPitching = pitching.next(pins);
        // then
        assertThat(nextPitching).isEqualTo(Pitching.of(pins));
    }

    @Test
    @DisplayName("점수 합계 반환")
    void score_pitching() {
        // given
        Pitching pitching = Pitching.first(10);
        Pitching nextPitching1 = pitching.next(10);
        Pitching nextPitching2 = nextPitching1.next(9);

        // when
        Integer score = pitching.score(2);
        Integer score1 = nextPitching1.score(1);
        Integer score2 = nextPitching2.score(0);

        // then
        assertThat(score).isEqualTo(29);
        assertThat(score1).isEqualTo(19);
        assertThat(score2).isEqualTo(9);
    }

}