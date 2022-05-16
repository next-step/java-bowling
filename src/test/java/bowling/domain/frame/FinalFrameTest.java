package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @ParameterizedTest(name = "스트라이크/스페어 일 때 추가 투구 안 하면 isFull false")
    @CsvSource({"10,10", "10,0", "9,1", "8,2"})
    void isFull_WhenExtraMissing_ReturnsFalse(int firstNo, int secondNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);

        assertThat(finalFrame.isFull()).isFalse();
    }

    @ParameterizedTest(name = "스트라이크/스페어 일 때 추가 투구하면 isFull true")
    @CsvSource({"10,10,10", "10,0,3", "9,1,2", "8,2,1"})
    void isFull_WhenExtraAdded_ReturnsTrue(int firstNo, int secondNo, int extraNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);
        finalFrame.addPin(extraNo);

        assertThat(finalFrame.isFull()).isTrue();
    }

    @ParameterizedTest(name = "미스면 isFull true")
    @CsvSource({"8,1", "0,0", "0,9", "4,5"})
    void isFull_WhenMiss_ReturnsTrue(int firstNo, int secondNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);

        assertThat(finalFrame.isFull()).isTrue();
    }

    @ParameterizedTest(name = "첫 번째 투구가 스트라이크가 아니면 두 투구의 합은 10이하")
    @CsvSource({"7,4", "9,3", "2,9"})
    void addPin_WhenValidationFailed_ThrowsIllegalStateException(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new FinalFrame(firstNo).addPin(secondNo))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getScore_Miss() {
        FinalFrame finalFrame = new FinalFrame(5);
        finalFrame.addPin(4);

        assertThat(finalFrame.getScore()).isEqualTo(9);
    }

    @Test
    void getScore_Spare() {
        FinalFrame finalFrame = new FinalFrame(5);
        finalFrame.addPin(5);
        finalFrame.addPin(5);

        assertThat(finalFrame.getScore()).isEqualTo(15);
    }

    @Test
    void getScore_Strike() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPin(5);
        finalFrame.addPin(5);

        assertThat(finalFrame.getScore()).isEqualTo(20);
    }
}
