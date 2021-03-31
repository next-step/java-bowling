package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class NormalFrameTest {

    @Test
    void first() {
        NormalFrame first = NormalFrame.first();
        assertThat(first.number()).isEqualTo(1);
    }

    @Test
    void next() {
        NormalFrame next = NormalFrame.first().next();
        assertThat(next.number()).isEqualTo(2);
    }

    @Test
    void last() {
        FinalFrame last = NormalFrame.first().last();
        assertThat(last.number()).isEqualTo(2);
    }

    @Test
    void add_pin_counts() {
        int pinCount = 3;
        NormalFrame first = NormalFrame.first();
        first.addPintCount(pinCount);
        List<Integer> pinCounts = first.pinCounts();

        assertThat(pinCounts).containsExactly(pinCount);
    }

    @Test
    @DisplayName("이미 종료된 프레임에 투구 추가시 예외 발생 테스트")
    void add_pin_counts_when_done_throw_exception() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.addPintCount(4);
        normalFrame.addPintCount(5);

        assertThat(normalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                normalFrame.addPintCount(5));
    }

    @Test
    @DisplayName("한번 투구에 스트라이크가 아닐시 종료 여부 테스트")
    void is_done_once_when_miss() {
        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.addPintCount(4);

        assertThat(normalFrame.isDone()).isFalse();
    }



    @Test
    @DisplayName("모든 핀을 쓸어뜨리지 못했을때 종료 여부 테스트")
    void is_done_when_miss() {
        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.addPintCount(4);
        normalFrame.addPintCount(5);

        assertThat(normalFrame.isDone()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 종료 여부 테스트")
    void is_done_when_strike() {
        int pinCount = 10;
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.addPintCount(pinCount);

        assertThat(normalFrame.isDone()).isTrue();
    }

    @Test
    @DisplayName("스페어 종료 여부 테스트")
    void is_done_when_spare() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.addPintCount(3);
        normalFrame.addPintCount(7);

        assertThat(normalFrame.isDone()).isTrue();
    }

}
