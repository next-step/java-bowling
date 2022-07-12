package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void 투구수가_10개_이상이면_안된() {
        Assertions.assertThatThrownBy(() -> {
            Frame frame = new NormalFrame();
            frame.throwBall(11);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("핀은 10개 이상 넘어갈 수 없습니다");
    }

    @Test
    void 투구는_2번_이상이면_안된다() {
        Assertions.assertThatThrownBy(() -> {
            Frame frame = new NormalFrame();
            frame.throwBall(2);
            frame.throwBall(2);
            frame.throwBall(2);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두번 이상 투구를 할 수 없습니다.");
    }

    @Test
    void 투구수_10개면_스트라이크다() {
        Frame frame = new NormalFrame();
        Assertions.assertThat( frame.throwBall(10)).isEqualTo(ScoreType.STRIKE);
    }

    @Test
    void 프레임에_두번쨰_투구에서_핀이_0개가_되면_스페어다() {
        Frame frame = new NormalFrame();
        Assertions.assertThat(frame.throwBall(8)).isEqualTo(ScoreType.MISS);
        Assertions.assertThat(frame.throwBall(2)).isEqualTo(ScoreType.SPARE);
    }

    @Test
    void 프레임의_두번재_투구에서도_모든_핀이_쓰러지지_않은_상태는_미스다() {
        Frame frame = new NormalFrame();
        Assertions.assertThat(frame.throwBall(3)).isEqualTo(ScoreType.MISS);
        Assertions.assertThat(frame.throwBall(4)).isEqualTo(ScoreType.MISS);
    }

    @Test
    void 핀을_하나도_쓰러트리지_못한_상태는_거터다() {
        Frame frame = new NormalFrame();
        Assertions.assertThat(frame.throwBall(0)).isEqualTo(ScoreType.GUTTER);
        Assertions.assertThat(frame.throwBall(4)).isEqualTo(ScoreType.MISS);

        Frame frame2 = new NormalFrame();
        Assertions.assertThat(frame2.throwBall(3)).isEqualTo(ScoreType.MISS);
        Assertions.assertThat(frame2.throwBall(0)).isEqualTo(ScoreType.GUTTER);
    }

    @Test
    void 마지막_프레임은_FinalFrame객체를_반환한() {
        Frame frame = new NormalFrame();
        Frame normalFrame = frame.next().next().next().next().next()
                .next().next().next().next();

        Assertions.assertThat(normalFrame).isInstanceOf(NormalFrame.class);
        Assertions.assertThat(normalFrame.next()).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 마지막_프레임은_스트라이크이거나_스페어가_아니면_Null을_반환한다() {
        Frame frame = new NormalFrame();
        Frame finalFrame = frame.next().next().next().next().next()
                .next().next().next().next().next();

        Assertions.assertThat(finalFrame).isInstanceOf(FinalFrame.class);
        Assertions.assertThat(finalFrame.next()).isNull();
    }

    @Test
    void 마지막_프레임은_스트라이크이거나_스페어이면_한_번을_더_FinalFrame객체를_반환한다() {
        Frame frame = new NormalFrame();
        Frame finalFrame = frame.next().next().next().next().next()
                .next().next().next().next().next();

        Assertions.assertThat(finalFrame.throwBall(10)).isEqualTo(ScoreType.STRIKE);
        Assertions.assertThat(finalFrame.next()).isInstanceOf(FinalFrame.class);
    }
}