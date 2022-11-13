package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    @DisplayName("스트라이크일때 포인트 더하기")
    void strike_calculate_point() {
        //given
        Point strike1 = Point.start();
        strike1.add(new Pin(10));
        //when
        strike1.add(new Pin(2));
        strike1.add(new Pin(2));
        //then
        assertThat(strike1.calculate()).isEqualTo(14);
    }


}