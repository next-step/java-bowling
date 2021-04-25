package bowling.state;

import bowling.domain.Pins;
import bowling.domain.exception.PinsCountException;
import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReadyTest {
    private Ready ready;

    @BeforeEach
    void setUp() {
        ready = Ready.create();
    }

    @Test
    @DisplayName("턴이 끝났는지 확인 테스트")
    void isFinishedTest() {
        assertThat(ready.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Continue 상태로 변경 확인 테스트")
    void bowlToContinueTest() {
        Pins pins = Pins.ofFirstPitch(7);
        assertThat(ready.bowl(7)).isEqualTo(Continue.of(pins));
    }

    @Test
    @DisplayName("Strike 상태로 변경 확인 테스트")
    void bowlToStrikeTest() {
        Pins pins = Pins.ofFirstPitch(10);
        assertThat(ready.bowl(10)).isEqualTo(Strike.create());
    }

    @ParameterizedTest(name = "넘어뜨린 핀 개수 예외 테스트")
    @ValueSource(ints = {11, -1, 15})
    void bowlToSpareTest(int pitch) {
        assertThatThrownBy(() -> ready.bowl(pitch))
                .isInstanceOf(PinsCountException.class)
                .hasMessage("넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.");
    }
}
