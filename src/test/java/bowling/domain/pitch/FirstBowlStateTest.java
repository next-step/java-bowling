package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FirstBowlStateTest {
    private FirstBowlState firstBowlState;

    @BeforeEach
    void setUp() {
        firstBowlState = new FirstBowlState(Pin.valueOf(1));
    }

    @DisplayName("미스를 리턴한다")
    @Test
    void returnBowl() {
        assertThat(firstBowlState.bowl(Pin.valueOf(8)))
                .isExactlyInstanceOf(MissState.class);
    }

    @DisplayName("스트라이크를 리턴한다")
    @Test
    void returnStrike() {
        assertThat(firstBowlState.bowl(Pin.valueOf(9)))
                .isExactlyInstanceOf(SpareState.class);
    }

    @DisplayName("이전 시도와 합쳐서 핀 갯수가 10이 넘어갈 수 없다.")
    @Test
    void error() {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> firstBowlState.bowl(Pin.valueOf(10)));
    }
}
