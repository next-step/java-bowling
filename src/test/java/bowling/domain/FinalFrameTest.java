package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @DisplayName("Frame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        Frame finalFrame = FinalFrame.newInstance();

        assertAll(
                () -> assertThat(finalFrame).isNotNull(),
                () -> assertThat(finalFrame).isInstanceOf(FinalFrame.class)
        );
    }
}