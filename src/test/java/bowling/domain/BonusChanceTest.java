package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("보너스 점수 기회")
public class BonusChanceTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    @DisplayName("추가 점수 가능")
    public void isScoreAddTrue(int bonus) throws Exception {
        //given
        BonusChance bonusChance = BonusChance.of(bonus);

        //when
        
        //then
        assertThat(bonusChance.isScoreAdd()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2,1", "1,0"})
    @DisplayName("추가 점수 감소 확인")
    public void bonusChanceMinus(int before, int check) throws Exception {
        //given
        BonusChance bonusChance = BonusChance.of(before);
        BonusChance checkChance = BonusChance.of(check);

        //when
        bonusChance = bonusChance.minus();

        //then
        assertThat(checkChance.equals(bonusChance)).isTrue();
    }

    @Test
    @DisplayName("추가 점수 불가")
    public void isScoreAddFalse() throws Exception {
        //given
        BonusChance bonusChance = BonusChance.of(0);

        //when

        //then
        assertThat(bonusChance.isScoreAdd()).isFalse();
    }

    @Test
    @DisplayName("최소 기회에 감소시 에러")
    public void minBonusMinusException() throws Exception {
        assertThatThrownBy(() -> BonusChance.of(0).minus())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최대 기회 초과 에러")
    public void maxOverException() throws Exception {
        assertThatThrownBy(() -> BonusChance.of(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최소 기회 미만 에러")
    public void minUnderException() throws Exception {
        assertThatThrownBy(() -> BonusChance.of(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
