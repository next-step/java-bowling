package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    private static Frame frame;

    @BeforeEach
    void beforeEach() {
        frame = NormalFrame.init();
    }

    @DisplayName("bowl 호출 후 종료 상태이면 새로운 Frame을 생성하여 추가한다.")
    @Test
    void frameStateStrikeOrSpareOrMissOrGutter() {
        assertThat(frame.bowl(Pin.of(10))).isEqualTo(NormalFrame.next(FrameIndex.of(2)));
    }

    @DisplayName("bowl 호출 후 종료 상태가 아니면 새로운 Frame을 생성하지 않고 현재 Frame을 반환한다.")
    @Test
    void frameStateReadyOrFirst() {
        assertThat(frame.getFrameIndex()).isEqualTo(frame.bowl(Pin.of(5)).getFrameIndex());
    }

    @DisplayName("bowl 호출 후 다음 index가 마지막인 경우 FinalFrame을 추가하고 반환한다.")
    @Test
    void finalFrame() {
        for (int i = 0; i < FrameIndex.MAX; i++) {
            frame = frame.bowl(Pin.of(10));
        }
        assertThat(frame).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("NormalFrame에서 첫 번째에 스트라이크 친 경우 한 번만 투구한다.")
    @Test
    void strike() {
        frame.bowl(Pin.of(10));
        assertThat(frame.symbol()).isEqualTo(State.STRIKE.getSymbol());
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("NormalFrame은 최대 2번 투구한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "5, 4, 5|4",
            "5, 0, 5|-",
            "0, 0, -|-",
            "0, 3, -|3",
            "8, 2, 8|/",
            "0, 10, -|/"
    })
    void twoBowls(int bowl1, int bowl2, String symbol) {
        List<Integer> pinNumbers = Arrays.asList(bowl1, bowl2);
        for (int pinNumber : pinNumbers) {
            frame.bowl(Pin.of(pinNumber));
        }
        assertThat(frame.symbol()).isEqualTo(symbol);
        assertThat(frame.isEnd()).isTrue();
    }
}
