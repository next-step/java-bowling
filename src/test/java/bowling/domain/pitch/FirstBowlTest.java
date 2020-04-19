package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FirstBowlTest {
    private FirstBowl firstBowl;

    @BeforeEach
    void setUp() {
        firstBowl = new FirstBowl(Pin.valueOf(1));
    }

    @DisplayName("미스를 리턴한다")
    @Test
    void returnBowl() {
        assertThat(firstBowl.bowl(Pin.valueOf(8)))
                .isExactlyInstanceOf(Miss.class);
    }

    @DisplayName("스트라이크를 리턴한다")
    @Test
    void returnStrike() {
        assertThat(firstBowl.bowl(Pin.valueOf(9)))
                .isExactlyInstanceOf(Spare.class);
    }

    @DisplayName("이전 시도와 합쳐서 핀 갯수가 10이 넘어갈 수 없다.")
    @Test
    void error() {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> firstBowl.bowl(Pin.valueOf(10)));
    }
}
