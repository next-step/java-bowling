package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("NormalFrame 생성 여부 테스트")
    @Test
    void 생성() {
        Frame normalFrame = NormalFrame.initialize();

        assertAll(
                () -> assertThat(normalFrame).isNotNull(),
                () -> assertThat(normalFrame).isInstanceOf(NormalFrame.class)
        );

    }
}