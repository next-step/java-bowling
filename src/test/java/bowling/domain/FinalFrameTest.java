package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.domain.FrameTest.PREVIOUS;
import static bowling.domain.FrameTest.STRIKE_BOWL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @DisplayName("NormalFrame 생성 테스트")
    @Test
    void from() {
        FinalFrame finalFrame = FinalFrame.from(PREVIOUS);

        assertThat(finalFrame).isEqualTo(FinalFrame.from(PREVIOUS));
        assertThat(finalFrame.canBowl()).isTrue();
    }

    @DisplayName("초구에서 스트라이크 후, 투구가 가능한지 확인 테스트")
    @Test
    void canBowl_firstBowl_strike_test() {
        NormalFrame normalFrame = NormalFrame.from(PREVIOUS);
        BowlResult initBowlResult = normalFrame.bowl(STRIKE_BOWL_COUNT);

        boolean actual = normalFrame.canBowl();

        assertThat(actual).isTrue();
    }

    @DisplayName("2구가 스페어거나 스트라이크인 경우 다음 투구가 가능한지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"0:10:true", "1:9:true", "3:3:false", "5:5:true"}, delimiter = ':')
    void canBowl_spare_test(int firstValue, int secondValue, boolean expectedResult) {
        NormalFrame normalFrame = NormalFrame.from(PREVIOUS);
        BowlResult firstBowlResult = normalFrame.bowl(firstValue);
        BowlResult secondBowlResult = normalFrame.bowl(secondValue);

        boolean actual = normalFrame.canBowl();

        assertThat(actual).isEqualTo(expectedResult);
    }
}
