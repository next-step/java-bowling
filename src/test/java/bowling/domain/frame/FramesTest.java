package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @DisplayName("Frames 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        Frames frames = Frames.initialize();

        assertThat(frames).isNotNull();
    }


    @DisplayName("Frames 인스턴스가 현재 Frame 의 순서를 반환하는지 테스트")
    @Test
    void 반환_순서() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.sequence()).isEqualTo(1);
    }
}