package bowling.domain.frame;

import bowling.domain.state.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @DisplayName("FinalFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame finalFrame = FinalFrame.initialize();

        // then
        assertAll(
                () -> assertThat(finalFrame).isNotNull(),
                () -> assertThat(finalFrame).isInstanceOf(FinalFrame.class)
        );
    }

    @DisplayName("FinalFrame 인스턴스가 bowl 실행 및 종료 여부 테스트")
    @Test
    void 투구_보너스_없을때() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(0);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame = finalFrame.bowl(firstPins);
        finalFrame = finalFrame.bowl(secondPins);

        // then
        assertThat(finalFrame.isFinish()).isTrue();
    }


}