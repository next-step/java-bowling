package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PointTest {

    @Test
    @DisplayName("스트라이크일때 포인트 더하기")
    void strike_calculate_point() {
        //given
        Point strike1 = new Point(null, 10, 2);
        //when
        strike1.add(new Pin(2));
        strike1.add(new Pin(2));
        //then
        assertAll(
                () -> assertThat(strike1.point()).isEqualTo(14),
                () -> assertThat(strike1.canAddPoint()).isFalse()
        );
    }
    
    @Test
    @DisplayName("포인트 누적 계산")
    void accumulate() {
        //given
        Pin turn1 = new Pin(10);
        Point frame1 = new Point(null, turn1.getValue(), 2);
        //when
        Pin turn2 = new Pin(10);
        Point frame2 = new Point(frame1, turn2.getValue(), 2);
        frame1.add(turn2);

        Pin turn3 = new Pin(5);
        Point frame3 = new Point(frame2,turn3.getValue(), 2);
        frame1.add(turn3);
        frame2.add(turn3);
        //then
        assertAll(
                () -> assertThat(frame1.point()).isEqualTo(25),
                () -> assertThat(frame2.point()).isEqualTo(40),
                () -> assertThat(frame3.point()).isEqualTo(45)
        );
    }

}