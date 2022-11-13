package bowling;

import bowling.domain.Chances;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChancesTest {

    @Test
    @DisplayName("모든 핀이 넘어졌음")
    void test2() {
        Chances chances = new Chances();
        chances.add(10);
        chances.add(0);
        assertThat(chances.areAllPinsDown()).isTrue();
    }

    @Test
    @DisplayName("남은 찬스가 없음")
    void test3() {
        Chances chances = new Chances();
        chances.add(10);
        chances.add(0);
        assertThat(chances.noLeftChances(false)).isTrue();
    }

    @Test
    @DisplayName("모든 핀이 넘어졌음")
    void test4() {
        Chances chances = new Chances();
        chances.add(5);
        chances.add(0);
        assertThat(chances.areAllPinsDown()).isFalse();
    }

    @Test
    @DisplayName("남은 찬스가 있음")
    void test5() {
        Chances chances = new Chances();
        chances.add(10);
        assertThat(chances.noLeftChances(false)).isFalse();
    }

}
