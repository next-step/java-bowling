package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @ParameterizedTest(name = "첫 번째 투구가 스트라이크가 아니면 두 투구의 합은 10이하")
    @CsvSource({"7,4", "9,3", "2,9"})
    void addPin_WhenValidationFailed_ThrowsIllegalStateException(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new FinalFrame(firstNo).addPin(secondNo))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = "스트라이크/스페어 일 때 추가 투구 안 하면 canGetScore false")
    @CsvSource({"10,10", "10,0", "9,1", "8,2"})
    void canGetScore_WhenExtraMissing_ReturnsFalse(int firstNo, int secondNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);

        assertThat(finalFrame.canGetScore()).isFalse();
    }

    @ParameterizedTest(name = "스트라이크/스페어 일 때 추가 투구하면 canGetScore true")
    @CsvSource({"10,10,10", "10,0,3", "9,1,2", "8,2,1"})
    void canGetScore_WhenExtraAdded_ReturnsTrue(int firstNo, int secondNo, int extraNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);
        finalFrame.addPin(extraNo);

        assertThat(finalFrame.canGetScore()).isTrue();
    }

    @ParameterizedTest(name = "미스면 canGetScore true")
    @CsvSource({"8,1", "0,0", "0,9", "4,5"})
    void canGetScore_WhenMiss_ReturnsTrue(int firstNo, int secondNo) {
        Frame finalFrame = new FinalFrame(firstNo);
        finalFrame.addPin(secondNo);

        assertThat(finalFrame.canGetScore()).isTrue();
    }

    @Test
    void score_Miss() {
        FinalFrame finalFrame = new FinalFrame(5);
        finalFrame.addPin(4);

        assertThat(finalFrame.score()).isPresent();
        assertThat(finalFrame.score()).contains(9);
    }

    @Test
    void score_Spare() {
        FinalFrame finalFrame = new FinalFrame(5);
        finalFrame.addPin(5);
        finalFrame.addPin(5);

        assertThat(finalFrame.score()).isPresent();
        assertThat(finalFrame.score()).contains(15);
    }

    @Test
    void score_Strike() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPin(5);
        finalFrame.addPin(5);

        assertThat(finalFrame.score()).isPresent();
        assertThat(finalFrame.score()).contains(20);
    }

    @Test
    void strikeBonusForPreviousFrame() {
        FinalFrame finalFrame = new FinalFrame(10);

        assertThat(finalFrame.strikeBonusForPreviousFrame()).isEmpty();
        finalFrame.addPin(10);
        assertThat(finalFrame.strikeBonusForPreviousFrame()).contains(20);
    }
}
