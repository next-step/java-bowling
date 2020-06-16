package bowling;

import bowling.domain.BowlingBuildingException;
import bowling.domain.BowlingPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingPinTest {

    @DisplayName("BowlingPin 객체 정상 생성")
    @Test
    public void bowlingPin_정상_생성() {
        assertThatCode(() -> {
            BowlingPin.of(0);
            BowlingPin.of(9);
        }).doesNotThrowAnyException();
    }

    @DisplayName("BowlingPin 객체 생성 시 인덱스 번호를 벗어난 경우 예외 발생")
    @Test
    public void bowlingPin_예외_발생() {
        assertThatThrownBy(() -> {
            BowlingPin.of(10);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PIN_INDEX);
    }
}
