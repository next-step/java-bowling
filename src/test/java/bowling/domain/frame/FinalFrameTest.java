package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class FinalFrameTest {

    @ParameterizedTest(name = "FinalFrame은 스트라이크/스페어일 때 보너스 투구 가능")
    @CsvSource({"7,3", "10,0", "10,10"})
    void addExtra(int firstNo, int secondNo) {
        assertThatNoException()
                .isThrownBy(() -> new FinalFrame(firstNo, secondNo).addExtra(10));
    }

    @ParameterizedTest(name = "FinalFrame이 스트라이크/스페어 아닐 때 보너스 투구하면 예외")
    @CsvSource({"6,3", "7,2", "0,0"})
    void addExtra_WhenNotAvailable_ThrowsIllegalStateException(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new FinalFrame(firstNo, secondNo).addExtra(10))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("FinalFrame은 첫 번째 투구 + 두 번째 투구 + 보너스 투구 세 가지가 같아야 동등")
    @Test
    void equals() {
        assertThat(new FinalFrame(5, 5).addExtra(5))
                .isEqualTo(new FinalFrame(5, 5).addExtra(5));

        assertThat(new FinalFrame(5, 5).addExtra(5))
                .isNotEqualTo(new FinalFrame(5, 5).addExtra(4));
    }
}
