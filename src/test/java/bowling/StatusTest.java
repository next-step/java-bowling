package bowling;

import bowling.domain.Point;
import bowling.domain.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @Test
    @DisplayName("스트라이크")
    void test1() {
        assertThat(Status.findStatus(0, Point.All_KNOCK_DOWN_POINT, null)).isEqualTo(Status.STRIKE);
    }

    @Test
    @DisplayName("스페어")
    void test2() {
        assertThat(Status.findStatus(1, Point.of(1), Point.All_KNOCK_DOWN_POINT)).isEqualTo(Status.SPARE);
    }

    @Test
    @DisplayName("미스")
    void test3() {
        assertThat(Status.findStatus(1, Point.NO_KNOCK_DOWN_POINT, Point.of(5))).isEqualTo(Status.MISS);
    }

    @Test
    @DisplayName("거터")
    void test4() {
        assertThat(Status.findStatus(0, Point.NO_KNOCK_DOWN_POINT, Point.NO_KNOCK_DOWN_POINT)).isEqualTo(Status.GUTTER);
    }

    @Test
    @DisplayName("Empty")
    void test6() {
        assertThat(Status.findStatus(0, Point.of(3), Point.of(3))).isEqualTo(Status.NONE);
    }

}
