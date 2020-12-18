package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created : 2020-12-16 오전 8:21
 * Developer : Seo
 */
class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(new Pins(10));
    }

    @DisplayName("생성")
    @Test
    void constructor() {
        assertThat(score).isNotNull().isInstanceOf(Score.class);
    }

    @DisplayName("점수 유효성 체크 ")
    @Test
    void invalid_first() {
        assertThatThrownBy(() -> new Score(new Pins(11)))
                .withFailMessage("잘못된 점수입니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 구 점수 체크")
    @Test
    void getFirst() {
        assertThat(score.getFirst()).isEqualTo(10);
    }

    @DisplayName("프레임 점수 체크")
    @Test
    void get_strike() {
        assertThat(score.get()).isEqualTo(Symbol.STRIKE.getScore());
    }

    @DisplayName("심볼 체크 - 스트라이크")
    @Test
    void get_symbol_strike() {
        score = new Score(new Pins(10));
        assertThat(score.getSymbol(0)).isEqualTo("X");
    }

    @DisplayName("심볼 체크 - 스페어")
    @Test
    void get_symbol_spare() {
        score = new Score(new Pins(9));
        score.setSecond(new Pins(1));
        assertThat(score.getSymbol(0)).isEqualTo("9|/");
    }

    @DisplayName("심볼 체크 - 거터")
    @Test
    void get_symbol_gutter() {
        score = new Score(new Pins(9));
        score.setSecond(new Pins(0));
        assertThat(score.getSymbol(0)).isEqualTo("9|-");

        score = new Score(new Pins(0));
        score.setSecond(new Pins(9));
        assertThat(score.getSymbol(0)).isEqualTo("-|9");

        score = new Score(new Pins(0));
        score.setSecond(new Pins(0));
        assertThat(score.getSymbol(0)).isEqualTo("-|-");
    }

    @DisplayName("심볼 체크 - 미스")
    @Test
    void get_symbol_miss() {
        score = new Score(new Pins(8));
        score.setSecond(new Pins(1));
        assertThat(score.getSymbol(0)).isEqualTo("8|1");
    }

    @DisplayName("심볼 체크 - 10프레임 보너스")
    @Test
    void lastSymbol() {
        score = new Score(new Pins(10));
        score.setSecond(new Pins(10));
        score.setTenFrameBonus(new Pins(10));
        assertThat(score.getSymbol(10)).isEqualTo("X|X|X");

        score = new Score(new Pins(10));
        score.setSecond(new Pins(10));
        score.setTenFrameBonus(new Pins(9));
        assertThat(score.getSymbol(10)).isEqualTo("X|X|9");

        score = new Score(new Pins(10));
        score.setSecond(new Pins(9));
        score.setTenFrameBonus(new Pins(10));
        assertThat(score.getSymbol(10)).isEqualTo("X|9|X");

        score = new Score(new Pins(9));
        score.setSecond(new Pins(10));
        score.setTenFrameBonus(new Pins(10));
        assertThat(score.getSymbol(10)).isEqualTo("9|X|X");

        score = new Score(new Pins(10));
        score.setSecond(new Pins(9));
        score.setTenFrameBonus(new Pins(9));
        assertThat(score.getSymbol(10)).isEqualTo("X|9|9");

        score = new Score(new Pins(9));
        score.setSecond(new Pins(10));
        score.setTenFrameBonus(new Pins(9));
        assertThat(score.getSymbol(10)).isEqualTo("9|X|9");

        score = new Score(new Pins(9));
        score.setSecond(new Pins(9));
        score.setTenFrameBonus(new Pins(10));
        assertThat(score.getSymbol(10)).isEqualTo("9|9|X");
    }
}
