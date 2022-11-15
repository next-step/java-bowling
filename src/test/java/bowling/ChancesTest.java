package bowling;

import bowling.domain.Chances;
import bowling.domain.Point;
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
        assertThat(chances.noLeftChancesInNormalFrame()).isTrue();
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
        assertThat(chances.noLeftChancesInNormalFrame()).isFalse();
    }

    @Test
    @DisplayName("STRIKE를 가지고 있음")
    void test6() {
        Chances chances = new Chances();
        chances.add(10);
        assertThat(chances.hasStrike()).isTrue();
    }

    @Test
    @DisplayName("SPARE를 가지고 있음")
    void test7() {
        Chances chances = new Chances();
        chances.add(5);
        chances.add(5);
        assertThat(chances.hasSpare()).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 찬스들의 Point 합치기")
    void test8() {
        Chances chances = new Chances();
        chances.add(5);
        chances.add(5);
        assertThat(chances.sumOfPoints()).isEqualTo(Point.of(10));
    }

}
