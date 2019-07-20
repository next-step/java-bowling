package domain.score;

import domain.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("점수 가져오기")
    void getScore() {
        Score score = Score.of(Pins.ALL, Pins.EMPTY, BonusType.strike());
        assertThat(score.getValue()).isEqualTo(10);
    }

    @Test
    @DisplayName("스코어 전체가 더하기")
    void strikeAddBonus() {
        Score score = Score.of(Pins.ALL, Pins.EMPTY, BonusType.strike());
        Score added = Score.of(Pins.of(5), Pins.of(4), BonusType.normal());
        Score calculated = score.calculate(added);
        assertThat(calculated.getValue()).isEqualTo(19);
    }

    @Test
    @DisplayName("스페어 타입은 first 만 더해진다.")
    void spareAddBonus() {
        Score score = Score.of(Pins.of(9), Pins.of(1), BonusType.spare());
        Score added = Score.of(Pins.of(5), Pins.EMPTY, BonusType.normal());
        Score calculated = score.calculate(added);
        assertThat(calculated.getValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("보너스가 없다.")
    void hasNotBonus() {
        Score score = Score.of(Pins.of(8), Pins.of(1), BonusType.normal());
        assertThat(score.hasBonus()).isFalse();
    }

    @Test
    @DisplayName("보너스가 존재한다")
    void hasBonus() {
        Score strike = Score.of(Pins.ALL, Pins.EMPTY, BonusType.strike());
        assertThat(strike.hasBonus()).isTrue();
        Score spare = Score.of(Pins.of(9), Pins.of(1), BonusType.spare());
        assertThat(spare.hasBonus()).isTrue();
    }
}