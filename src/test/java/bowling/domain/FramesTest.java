package bowling.domain;

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

    @DisplayName("Frames 인스턴스 인덱스 반환 여부 테스트")
    @Test
    void 반환_인덱스() {
        Frames frames = Frames.initialize();

        assertThat(frames.index()).isEqualTo(1);
    }

    @DisplayName("Frames 인스턴스 현재 frame 종료 여부 테스트")
    @Test
    void 반환_상태() {
        Frames frames = Frames.initialize();

        assertThat(frames.isFinish()).isEqualTo(false);
    }


}