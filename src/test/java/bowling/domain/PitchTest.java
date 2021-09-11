package bowling.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchTest {

    @ParameterizedTest(name = "투구 생성 - 0 ~ 10 이외의 값 | {arguments}")
    @ValueSource(ints = {-1, 11})
    public void 투구생성_실패(int pitchNumber) {
        // given
        String message = String.format("투구 값은 0 ~ 10 사이의 값이어야 합니다 -> %d", pitchNumber);

        // when
        ThrowingCallable throwingCallable = () -> new Pitch(pitchNumber);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest(name = "투구 생성 - 0 ~ 10 사이의 값 | {arguments}")
    @ValueSource(ints = {0, 10})
    public void 투구생성_성공(int pitchNumber) {
        // given
        Pitch expectedPitch = new Pitch(pitchNumber);

        // when
        Pitch pitch = new Pitch(pitchNumber);

        // then
        assertThat(pitch).isEqualTo(expectedPitch);
    }

}