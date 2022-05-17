package bowling.domain.frame;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.frameresult.Spare;
import bowling.domain.frameresult.Strike;
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

        assertThat(finalFrame.canGetResult()).isFalse();
    }

    @ParameterizedTest(name = "스트라이크/스페어 일 때 추가 투구하면 isFull true")
    @CsvSource({"10,10,10", "10,0,3", "9,1,2", "8,2,1"})
    void isFull_WhenExtraAdded_ReturnsTrue(int firstNo, int secondNo, int extraNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);
        finalFrame.addPin(extraNo);

        assertThat(finalFrame.canGetResult()).isTrue();
    }

    @ParameterizedTest(name = "미스면 isFull true")
    @CsvSource({"8,1", "0,0", "0,9", "4,5"})
    void isFull_WhenMiss_ReturnsTrue(int firstNo, int secondNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);

        assertThat(finalFrame.canGetResult()).isTrue();
    }

    @ParameterizedTest(name = "첫 번째 투구가 스트라이크가 아니면 두 투구의 합은 10이하")
    @CsvSource({"7,4", "9,3", "2,9"})
    void addPin_WhenValidationFailed_ThrowsIllegalStateException(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new FinalFrame(firstNo).addPin(secondNo))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getResult_Miss() {
        FinalFrame finalFrame = new FinalFrame(5);
        finalFrame.addPin(4);

        FrameResult result = finalFrame.getResult();

        assertThat(result).isInstanceOf(Miss.class);
        assertThat(result.calculateScore()).isEqualTo(9);
    }

    @Test
    void getResult_Spare() {
        FinalFrame finalFrame = new FinalFrame(5);
        finalFrame.addPin(5);
        finalFrame.addPin(5);

        FrameResult result = finalFrame.getResult();
        assertThat(result).isInstanceOf(Spare.class);
        assertThat(result.calculateScore()).isEqualTo(15);
    }

    @Test
    void getResult_Strike() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPin(5);
        finalFrame.addPin(5);

        FrameResult result = finalFrame.getResult();

        assertThat(result).isInstanceOf(Strike.class);
        assertThat(result.calculateScore()).isEqualTo(20);
    }

    @Test
    void strikeBonusForPreviousFrame() {
        FinalFrame finalFrame = new FinalFrame(10);

        assertThat(finalFrame.strikeBonusForPreviousFrame()).isEmpty();
        finalFrame.addPin(10);
        assertThat(finalFrame.strikeBonusForPreviousFrame()).hasValue(20);
    }
}
