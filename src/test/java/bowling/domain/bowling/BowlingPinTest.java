package bowling.domain.bowling;

import bowling.domain.exception.BowlingBuildingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BowlingPinTest {

    @DisplayName("BowlingPin 객체는 index 번호를 통해 cache에서 가져옴")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 9})
    public void makeBowlingPin_정상(int index) {
        assertThatCode(() -> {
            BowlingPin.of(index);
        }).doesNotThrowAnyException();
    }

    @DisplayName("BowlingPin 객체를 꺼낼 때 유효하지 않은 index 번호는 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 10})
    public void makeBowlingPin_예외(int index) {
        assertThatThrownBy(() -> {
            BowlingPin.of(index);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_BOWLING_PIN_INDEX);
    }

    @DisplayName("다음 투구를 위해 Initate 요청을 보내면 상태인 isStanding이 true로 변경")
    @Test
    public void initiate_True() {
        BowlingPin bowlingPin = BowlingPin.of(0);

        assertThat(bowlingPin.isStanding()).isFalse();

        bowlingPin.initiate();

        assertThat(bowlingPin.isStanding()).isTrue();
    }
}
