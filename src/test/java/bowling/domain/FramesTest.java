package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("프레임 일급 컬렉션 투구 테스트")
    @Test
    void currentFrame_프레임_투구테스트() {
        // given
        Frames frames = new Frames(Frame.init());
        // when
        frames.bowl(5);
        // then
        assertThat(frames.currentFrame()).isEqualTo(Frame.valueOf(1, 5));
    }

    @DisplayName("다음 프레임 생성 여부 테스트")
    @Test
    void isNextFrame_다음_프레임_확인테스트() {
        // given
        Frames frames = new Frames(Frame.valueOf(0, 0));
        // when
        frames.bowl(3);
        frames.bowl(7);
        // then
        assertThat(frames.isNextFrame()).isTrue();
    }

    @DisplayName("투구 시 시도 횟수 확인 테스트")
    @Test
    void tryCount_시도횟수_확인테스트() {
        Frames frames = new Frames(Frame.valueOf(0, 0));
        // when
        frames.bowl(3);
        frames.bowl(7);
        frames.bowl(6);

        assertThat(frames.tryCount()).isEqualTo(1);
    }
}
