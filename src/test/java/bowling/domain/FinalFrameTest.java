package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {
    private static Frame frame;

    @BeforeEach
    public void before() {
        frame = FinalFrame.init();
    }

    @DisplayName("FinalFrame의 index는 Index의 Max다.")
    @Test
    void index() {
        assertThat(frame.getFrameIndex()).isEqualTo(Index.MAX_INDEX);
    }

    @DisplayName("FinalFrame은 최소 2번 투구한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "5, 4, 5|4",
            "5, 0, 5|-",
            "0, 0, -",
            "0, 3, -|3"
    })
    void twoBowls(int bowl1, int bowl2, String symbol) {
        List<Integer> pinNumbers = Arrays.asList(bowl1, bowl2);
        for (int pinNumber : pinNumbers) {
            frame.bowl(Pin.of(pinNumber));
        }
        assertThat(frame.symbol()).isEqualTo(symbol);
    }

    @DisplayName("FinalFrame은 첫 번째에 스트라이크이거나, 두 번째에서 스페어 처리한 경우 한 번 더 투구할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {
            "10, 10, 10, X|X|X",
            "10, 10, 5, X|X|5",
            "10, 3, 7, X|3|/",
            "10, 5, 3, X|5|3",
            "6, 4, 3, 6|/|3"
    })
    void threeBowls(int bowl1, int bowl2, int bowl3, String symbol) {
        List<Integer> pinNumbers = Arrays.asList(bowl1, bowl2, bowl3);
        for (int pinNumber : pinNumbers) {
            frame.bowl(Pin.of(pinNumber));
        }
        assertThat(frame.symbol()).isEqualTo(symbol);
    }
}
