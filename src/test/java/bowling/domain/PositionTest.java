package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("포지션은 처음 시작은 0이다.")
    @Test
    void case1() {
        Position position = Position.first();

        assertThat(position).isEqualTo(Position.of(0));
    }

    @DisplayName("포지션이 한칸 증가한 포지션을 반환한다.")
    @Test
    void case2() {
        Position position = Position.first();

        assertThat(position.next()).isEqualTo(Position.of(1));
    }
}