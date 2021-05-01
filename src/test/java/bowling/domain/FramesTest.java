package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @DisplayName("Frames 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames).isNotNull();
    }

    @DisplayName("Frames 인스턴스 인덱스 반환 여부 테스트")
    @Test
    void 반환_인덱스() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.index()).isEqualTo(1);
    }

    @DisplayName("Frames 인스턴스 현재 frame 종료 여부 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.isFinish()).isEqualTo(false);
    }

    @DisplayName("Frames 인스턴스가 소유한 List<frame> 반환 여부 테스트")
    @Test
    void 반환_전체_리스트() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.frames()).isInstanceOf(List.class);
    }

    @DisplayName("Frames 인스턴스가 bowl() 호츌시 알맞은 값을 반환하는지 테스트")
    @Test
    void 볼링() {
        // given
        Frames frames = Frames.initialize();

        // when
        frames.bowl(HitCount.valueOf(9));

        // then
        assertThat(frames.isFinish()).isFalse();
    }



}