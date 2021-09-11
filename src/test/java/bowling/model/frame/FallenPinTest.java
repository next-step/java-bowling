package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("볼링 쓰러진 핀 테스트")
public class FallenPinTest {

    @DisplayName("쓰러진 핀 개수가 0개 이상 10개 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void outOfRangeFallenPinCountExceptionTest(int fallenPinCount) {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FallenPin.of(fallenPinCount))
                .withMessage("쓰러진 핀은 0개 이상 10개 이하여야 합니다.");
    }
}
