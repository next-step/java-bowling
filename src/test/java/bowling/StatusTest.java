package bowling;

import bowling.domain.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @Test
    @DisplayName("스트라이크")
    void test1() {
        assertThat(Status.findStatus(0, true, false)).isEqualTo(Status.STRIKE);
    }

    @Test
    @DisplayName("스페어")
    void test2() {
        assertThat(Status.findStatus(1, true, false)).isEqualTo(Status.SPARE);
    }

    @Test
    @DisplayName("미스")
    void test3() {
        assertThat(Status.findStatus(1, false, false)).isEqualTo(Status.MISS);
    }

    @Test
    @DisplayName("거터")
    void test4() {
        assertThat(Status.findStatus(0, false, true)).isEqualTo(Status.GUTTER);
    }

    @Test
    @DisplayName("거터")
    void test5() {
        assertThat(Status.findStatus(1, false, true)).isEqualTo(Status.GUTTER);
    }

    @Test
    @DisplayName("Empty")
    void test6() {
        assertThat(Status.findStatus(0, false, false)).isEqualTo(Status.NONE);
    }

}
