package bowling.domain.frame;

import bowling.domain.pin.PinStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @DisplayName("firstNo와 secondNo의 합은 10이하")
    @Test
    void validation() {
        assertThatThrownBy(() -> new NormalFrame(1, 7, 4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getStatus() {
        assertThat(new NormalFrame(1, 10, 0).getStatus())
                .isSameAs(PinStatus.STRIKE);
    }

    @Test
    void next() {
        assertThat(new NormalFrame(1, 0, 0).next(0, 0))
                .isEqualTo(new NormalFrame(2, 0, 0));
    }

    @Test
    void next_WhenLastFrame_ReturnsFinalFrame() {
        assertThat(new NormalFrame(9, 0, 0).next(0, 0))
                .isEqualTo(new FinalFrame(0, 0));
    }

    @DisplayName("NormalFrame은 프레임 번호와 첫 번째+두 번째 투구가 모두 같아야 동등")
    @Test
    void equals() {
        assertThat(new NormalFrame(5, 5, 5))
                .isEqualTo(new NormalFrame(5, 5, 5));

        assertThat(new NormalFrame(5, 5, 5))
                .isNotEqualTo(new NormalFrame(5, 5, 4));

        assertThat(new NormalFrame(5, 5, 5))
                .isNotEqualTo(new NormalFrame(4, 5, 5));
    }

}
