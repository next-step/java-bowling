package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class FrameNumberTest {

    @Test
    void from_정상_생성() {
        assertThat(FrameNumber.from(1)).isEqualTo(FrameNumber.from(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, -1})
    void from_범위를_벗어난_값(int number){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FrameNumber.from(number))
                .withMessage("범위를 벗어난 프레임 수 입니다.");
    }

    @Test
    void next_정상() {
        assertThat(FrameNumber.from(1).next()).isEqualTo(FrameNumber.from(2));
    }

    @Test
    void next_마지막_FrameNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FrameNumber.from(FrameNumber.MAX_FRAME_NUMBER).next())
                .withMessage("범위를 벗어난 프레임 수 입니다.");
    }

    @Test
    @DisplayName("마지막 이전 값 체크")
    void beforeLast() {
        assertThat(FrameNumber.from(FrameNumber.MAX_FRAME_NUMBER).beforeLast()).isFalse();
        assertThat(FrameNumber.from(FrameNumber.MAX_FRAME_NUMBER-1).beforeLast()).isTrue();
    }
}