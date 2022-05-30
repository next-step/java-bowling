package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    private final int firstPins = 7;
    private final int secondPins = 3;
    private final int nextPins = 10;

    @Test
    @DisplayName("1/2차 투구 후 다음 프레임 생성")
    void nextFrame() {
        Frame first = NormalFrame.bowling(8, firstPins).bowling(secondPins);

        assertThat(first.next(nextPins)).isNotEqualTo(first);
    }

    @Test
    @DisplayName("9번째 프레임 종료 후, FinalFrame 생성")
    void finalNext() {
        Frame semiFinal = NormalFrame.bowling(9, firstPins).bowling(secondPins);

        assertThat(semiFinal.next(nextPins)).isInstanceOf(FinalFrame.class);
    }
}
