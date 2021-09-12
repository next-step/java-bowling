package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("볼링 쓰러진 핀 테스트")
public class FallenPinTest {

    @DisplayName("쓰러진 핀 개수가 0개 이상 10개 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void outOfRangeFallenPinCountExceptionTest(int fallenPinCount) {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FallenPin.from(fallenPinCount))
                .withMessage("쓰러진 핀은 0개 이상 10개 이하여야 합니다.");
    }

    @DisplayName("핀 개수가 1개 이상 10 이하면 개수에 맞게 정상 생성되어야 한다.")
    @Test
    void createFallenPinTest() {
        // given, when, then
        for (int fallenPinCount = 1; fallenPinCount < 10; fallenPinCount++) {
            assertEquals(FallenPin.from(fallenPinCount).count(), fallenPinCount);
        }
    }
}
