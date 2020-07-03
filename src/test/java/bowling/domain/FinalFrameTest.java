package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("초기 상태 final frame 인스턴스 확인")
    @Test
    void finalFrameInit() {
        Frame frame = Frame.ofFinal();
        assertThat(frame instanceof FinalFrame).isTrue();
    }

}
