package bowling;

import bowling.domain.Chance;
import bowling.domain.Point;
import bowling.domain.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChanceTest {
    @Test
    @DisplayName("Status가 NONE이면 숫자를")
    void test1() {
        Chance chance = new Chance(Point.of(1), Status.NONE);
        assertThat(chance.chance()).isEqualTo("1");
    }

    @Test
    @DisplayName("Status가 NONE이 아니라면 Status를")
    void test2() {
        Chance chance = new Chance(Point.of(1), Status.STRIKE);
        assertThat(chance.chance()).isEqualTo("X");
    }

    @Test
    @DisplayName("현재 찬스가 STRIKE인지 확인")
    void test3() {
        // given
        Chance chance = new Chance(Point.of(10), Status.STRIKE);
        // when
        boolean isStrike = chance.isStrike();
        // then
        assertThat(isStrike).isTrue();
    }

    @Test
    @DisplayName("현재 찬스가 SPARE인지 확인")
    void test4() {
        // given
        Chance chance = new Chance(Point.of(10), Status.SPARE);
        // when
        boolean isSpare = chance.isSPARE();
        // then
        assertThat(isSpare).isTrue();
    }

}
