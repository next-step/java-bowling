package bowling.domain.frame;

import bowling.domain.state.Pins;
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


    @DisplayName("Frames 인스턴스 현재 frame 종료 여부 테스트")
    @Test
    void 종료() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.isFinish()).isFalse();
    }

    @DisplayName("Frames 인스턴스가 모든 bowl() 호츌시 종료 여부 반환하는지 테스트")
    @Test
    void 투구_bowl() {
        // given
        Frames frames = Frames.initialize();

        // when
        for (int i = 0; i < 12; i++) {
            frames.bowl(Pins.valueOf(10));
        }

        // then
        assertAll(
                () -> assertThat(frames.isFinish()).isTrue(),
                () -> assertThat(frames.sequence()).isEqualTo(10)
        );
    }


}