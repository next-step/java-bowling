package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FakeFrameTest {

    @DisplayName("FakeFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertAll(
                () -> assertThat(fakeFrame).isNotNull(),
                () -> assertThat(fakeFrame).isInstanceOf(FakeFrame.class)
        );
    }
}