package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StrikeTest {

    @Test
    void valueOfStrike() {
        assertThat(Strike.valueOf(1)).isEqualTo(Strike.STRIKE);
        assertThat(Strike.valueOf(2)).isEqualTo(Strike.DOUBLE);
        assertThat(Strike.valueOf(3)).isEqualTo(Strike.TURKEY);
        assertThat(Strike.valueOf(4)).isEqualTo(Strike.FOUR_BEGGER);
        assertThat(Strike.valueOf(5)).isEqualTo(Strike.FIVE_BEGGER);
        assertThat(Strike.valueOf(6)).isEqualTo(Strike.SIX_BEGGER);
        assertThat(Strike.valueOf(7)).isEqualTo(Strike.SEVEN_IN_A_ROW);
        assertThat(Strike.valueOf(8)).isEqualTo(Strike.EIGHT_STRIKE);
        assertThat(Strike.valueOf(9)).isEqualTo(Strike.NINE_STRIKE);
        assertThat(Strike.valueOf(10)).isEqualTo(Strike.TEN_STRIKE);
        assertThat(Strike.valueOf(11)).isEqualTo(Strike.ELEVEN_STRIKE);
        assertThat(Strike.valueOf(12)).isEqualTo(Strike.PERFERT_GAME);
    }

}
