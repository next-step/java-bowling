package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest {

    @Test
    void create() {
        int finalFrameNumber = 10;
        FinalFrame finalFrame = new FinalFrame(finalFrameNumber);
        assertThat(finalFrame.number()).isEqualTo(finalFrameNumber);
    }

    @Test
    void add_pin_counts() {
        int pinCount = 5;
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(pinCount);
        List<Integer> pinCounts = finalFrame.pinCounts();

        assertThat(pinCounts).containsExactly(pinCount);
    }

    @Test
    @DisplayName("이미 종료된 프레임에 투구 추가시 예외 발생 테스트")
    void add_pin_counts_when_done_throw_exception() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(3);
        finalFrame.addPintCount(5);

        assertThat(finalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                finalFrame.addPintCount(5));
    }

    @Test
    @DisplayName("두번 투구후 모든 핀을 쓸어뜨리지 못했을때 종료 여부 테스트")
    void is_done_when_miss() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(3);
        finalFrame.addPintCount(5);

        assertThat(finalFrame.isDone()).isTrue();
    }

    @Test
    @DisplayName("두번 투구후 스페어 처리후 종료 여부 테스트")
    void is_done_when_spare_at_second_try() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(3);
        finalFrame.addPintCount(7);

        assertThat(finalFrame.isDone()).isFalse();
    }

    @Test
    @DisplayName("연속 두번 스트라이크 후 종료 여부 테스트")
    void is_done_when_strike_twice_at_row() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(10);
        finalFrame.addPintCount(10);

        assertThat(finalFrame.isDone()).isFalse();
    }


    @Test
    @DisplayName("스트라이크 미스 후 종료 여부 테스트")
    void is_done_when_strike_miss() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(10);
        finalFrame.addPintCount(6);

        assertThat(finalFrame.isDone()).isFalse();
    }


}
