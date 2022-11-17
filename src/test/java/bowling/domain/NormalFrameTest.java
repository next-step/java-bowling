package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    @DisplayName("스트라이크")
    void strike() {
        Frame frame = new NormalFrame();
        frame.firstThrow(new Hit(10));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(10));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.STRIKE);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(0));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.SKIP);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스페어")
    void spare() {
        Frame frame = new NormalFrame();
        frame.firstThrow(new Hit(8));
        frame.secondThrow(new Hit(2));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(8));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.MISS);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(2));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.SPARE);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("거터")
    void gutter() {
        Frame frame = new NormalFrame();
        frame.firstThrow(new Hit(0));
        frame.secondThrow(new Hit(5));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(0));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.GUTTER);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(5));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.MISS);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("두번의 투구 모두 거터")
    void gutter_2() {
        Frame frame = new NormalFrame();
        frame.firstThrow(new Hit(0));
        frame.secondThrow(new Hit(0));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(0));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.GUTTER);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(0));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.GUTTER);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("종료되지 않은 상태 테스트")
    void isEnd() {
        Frame frame = new NormalFrame();

        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("한번 던져서 스트라이크를 치지 않은 뒤 종료되지 않은 상태 테스트")
    void isEnd_afterFirst() {
        Frame frame = new NormalFrame();
        frame.firstThrow(new Hit(5));

        assertThat(frame.isEnd()).isFalse();
    }
}
