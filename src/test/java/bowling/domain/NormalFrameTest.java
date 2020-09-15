package bowling.domain;

import bowling.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.FrameTest.PREVIOUS;
import static bowling.domain.FrameTest.STRIKE_BOWL_COUNT;
import static org.assertj.core.api.Assertions.*;

public class NormalFrameTest {

    @DisplayName("NormalFrame 생성 테스트")
    @Test
    void create() {
        NormalFrame normalFrame = NormalFrame.create();

        assertThat(normalFrame).isEqualTo(NormalFrame.from(PREVIOUS));
        assertThat(normalFrame.canBowl()).isTrue();
    }

    @DisplayName("NormalFrame 생성 테스트 - 이전 프레임이 존재하는 경우")
    @Test
    void from() {
        NormalFrame normalFrame = NormalFrame.from(PREVIOUS);

        assertThat(normalFrame).isEqualTo(NormalFrame.from(PREVIOUS));
        assertThat(normalFrame.canBowl()).isTrue();
    }

    @DisplayName("NormalFrame 투구 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"22", "-1"})
    void bowl_invalid_value(int firstValue) {
        NormalFrame normalFrame = NormalFrame.create();
        String exceptionMessage = ExceptionMessage.INVALID_SCORE_VALUE;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> normalFrame.bowl(firstValue))
                .withMessage(exceptionMessage);
    }

    @DisplayName("초구 후, 2구가 가능한지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:true", "10:false", "9:true"})
    void canBowl_(int firstValue, boolean expectedResult) {
        NormalFrame normalFrame = NormalFrame.create();
        BowlResult firstResult = normalFrame.bowl(firstValue);

        boolean actual = normalFrame.canBowl();

        assertThat(actual).isEqualTo(expectedResult);
    }

    @DisplayName("2구가 불가능한 상황에서 2구 진행 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:true", "10:false", "9:true"})
    void canBowl(int firstValue, boolean expectedResult) {
        NormalFrame normalFrame = NormalFrame.create();
        BowlResult firstResult = normalFrame.bowl(firstValue);

        boolean actual = normalFrame.canBowl();

        assertThat(actual).isEqualTo(expectedResult);
    }

    @DisplayName("스트라이크로 2구가 불가능한 상황에서 2구 진행 테스트")
    @Test
    void second_bowl_impossible_test(int secondValue) {
        String exceptionMessage = ExceptionMessage.NEXT_BOWL_IS_IMPOSSIBLE;

        NormalFrame normalFrame = NormalFrame.create();
        normalFrame.bowl(STRIKE_BOWL_COUNT);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> normalFrame.bowl(secondValue))
                .withMessageContaining(exceptionMessage)
        ;
    }

}
