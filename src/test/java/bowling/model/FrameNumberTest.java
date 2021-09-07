package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("볼링 프레임 번호 테스트")
public class FrameNumberTest {

    @DisplayName("프레임 번호가 1미만 10 초과이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void outOfBoundFrameNumberExceptionTest(int number) {
        // given
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FrameNumber(number))
                .withMessage("프레임 번호는 1이상 10 이하이어야 합니다.");
    }
}
