package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {

    @Test
    @DisplayName("첫번째 투구에 스트라이크를 친 경우 3회째 투구 가능")
    void strike_first() {
        Frame frame = new LastFrame();
        frame.play(new Hit(10));
        frame.play(new Hit(5));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(10));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.STRIKE);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(5));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.MISS);
        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("스페어인 경우 세번째 투구 가능")
    void spare() {
        Frame frame = new LastFrame();
        frame.play(new Hit(8));
        frame.play(new Hit(2));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(8));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.MISS);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(2));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.SPARE);
        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("3회 스트라이크")
    void strike() {
        Frame frame = new LastFrame();
        frame.play(new Hit(10));
        frame.play(new Hit(10));
        frame.play(new Hit(10));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(10));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.STRIKE);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(10));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.STRIKE);
        assertThat(frame.getThirdHit()).isEqualTo(new Hit(10));
        assertThat(frame.getThirdStatus()).isEqualTo(FrameStatus.STRIKE);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("거터")
    void gutter() {
        Frame frame = new LastFrame();
        frame.play(new Hit(0));
        frame.play(new Hit(5));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(0));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.GUTTER);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(5));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.MISS);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("두번의 투구 모두 거터")
    void gutter_2() {
        Frame frame = new LastFrame();
        frame.play(new Hit(0));
        frame.play(new Hit(0));

        assertThat(frame.getFirstHit()).isEqualTo(new Hit(0));
        assertThat(frame.getFirstStatus()).isEqualTo(FrameStatus.GUTTER);
        assertThat(frame.getSecondHit()).isEqualTo(new Hit(0));
        assertThat(frame.getSecondStatus()).isEqualTo(FrameStatus.GUTTER);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("종료되지 않은 상태 테스트")
    void isEnd() {
        Frame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("한번 던져서 스트라이크를 치지 않은 뒤 종료되지 않은 상태 테스트")
    void isEnd_afterFirst() {
        Frame frame = new LastFrame();
        frame.play(new Hit(5));

        assertThat(frame.isEnd()).isFalse();
    }
}
