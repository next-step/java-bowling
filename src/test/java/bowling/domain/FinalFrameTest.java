package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @ParameterizedTest(name = "마지막 프레임 종료 조건 테스트")
    @CsvSource({
            "TWO, TWO, UNDEFINED, true", "THREE, SEVEN, TEN, true", "TEN, THREE, SEVEN, true",
            "TWO, EIGHT, UNDEFINED, false", "TEN, TEN, UNDEFINED, false", "TEN, UNDEFINED, UNDEFINED, false"
    })
    public void finishTest(PinCount first, PinCount second, PinCount third, boolean expected) {
        Frame frame = new FinalFrame(first, second, third);
        assertThat(frame.isFinished()).isEqualTo(expected);
    }

}