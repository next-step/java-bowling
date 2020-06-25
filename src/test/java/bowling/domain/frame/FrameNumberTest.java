package bowling.domain.frame;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FrameNumberTest {

    @DisplayName("프레임 번호가 1 ~ 10 이외의 숫자인 경우 예외 반환")
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 11 })
    void validRange(final int count) {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> FrameNumber.of(count)
        );
    }

    @DisplayName("프레임 번호의 논리적 동치성 비교 및 생성 확인")
    @Test
    public void create() {
        final int count = 1;
        assertThat(FrameNumber.of(count))
                .isEqualTo(FrameNumber.of(count));
    }

    @DisplayName("다음 프레임 단계 만큼 증가한 프레임 번호를 반환")
    @ParameterizedTest
    @ValueSource(ints = { 1, 9 })
    void increase(final int number) {
        assertThat(FrameNumber.of(number).increase().getNo())
                .isEqualTo(number + FrameNumber.FRAME_STEP);
    }

    @DisplayName("마지막 프레임이면 return true 반환")
    @Test
    public void isFinal() {
        assertThat(FrameNumber.of(FrameNumber.MAX_NUMBER).isFinal())
                .isTrue();
    }
}
