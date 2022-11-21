package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FrameTest {

    @Test
    void 일반프레임_종료판단_2회투구() {
        Frame frame = Frame.createFirst();

        frame.bowling(5);
        assertThat(frame.isEnd()).isFalse();

        frame.bowling(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 일반프레임_종료판단_스트라이크() {
        Frame frame = Frame.createFirst();
        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 마지막프레임_종료판단_3회투구() {
        Frame first = Frame.createFirst();
        first.bowling(10);

        Frame frame = first.createFinal();
        frame.bowling(10);
        frame.bowling(10);
        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 프레임종료_예외() {
        Frame first = Frame.createFirst();
        first.bowling(10);

        Frame frame = first.createFinal();
        frame.bowling(10);
        frame.bowling(10);
        frame.bowling(10);

        assertThatIllegalStateException().isThrownBy(() -> frame.bowling(9));
    }

    @Test
    void 프레임종료_예외2() {
        Frame frame = Frame.createFirst();
        frame.bowling(5);
        frame.bowling(5);
        assertThat(frame.isEnd()).isTrue();

        assertThatIllegalStateException().isThrownBy(() -> frame.bowling(9));
    }

    @Test
    void 다음프레임생성() {
        Frame first = Frame.createFirst();
        first.bowling(2);
        first.bowling(2);
        Frame next = first.createNext();

        assertThat(first.getScore().get()).isEqualTo(next.getBeforeScore());
    }
    
    // ==============================================================================

    @Test
    void 노말점수_계산_미스() {
        Frame frame = Frame.createFirst();
        frame.bowling(2);
        frame.bowling(2);

        assertThat(frame.getScore().get()).isEqualTo(Score.of(4));
    }

    @Test
    void 노말점수_계산_스트라이크() {
        Frame first = Frame.createFirst();
        first.bowling(2);
        first.bowling(2);

        Frame next = first.createNext();
        next.bowling(10);

        Frame next1 = next.createNext();
        next1.bowling(10);

        Frame next2 = next1.createNext();
        next2.bowling(2);

        assertThat(next.getScore().get()).isEqualTo(Score.of(26));
    }

    @Test
    void 노말점수_계산_스페어() {
        Frame first = Frame.createFirst();
        first.bowling(2);
        first.bowling(2);

        Frame next = first.createNext();
        next.bowling(5);
        next.bowling(5);

        Frame nextNext = next.createNext();
        nextNext.bowling(2);
        nextNext.bowling(2);

        assertThat(next.getScore().get()).isEqualTo(Score.of(16));
    }

    @Test
    void 마지막점수_계산_미스() {
        Frame first = Frame.createFirst();
        first.bowling(2);
        first.bowling(2);

        Frame finalFrame = first.createFinal();
        finalFrame.bowling(2);
        finalFrame.bowling(2);
        
        assertThat(finalFrame.getScore().get()).isEqualTo(Score.of(8));
    }

    @Test
    void 마지막점수_계산_3스트라이크() {
        Frame first = Frame.createFirst();
        first.bowling(2);
        first.bowling(2);

        Frame finalFrame = first.createFinal();
        finalFrame.bowling(10);
        finalFrame.bowling(10);
        finalFrame.bowling(10);

        assertThat(finalFrame.getScore().get()).isEqualTo(Score.of(34));
    }

    @Test
    void 마지막점수_계산_스페어() {
        Frame first = Frame.createFirst();
        first.bowling(2);
        first.bowling(2);

        Frame finalFrame = first.createFinal();
        finalFrame.bowling(5); // 5
        finalFrame.bowling(5); // 5
        finalFrame.bowling(10); // 10

        assertThat(finalFrame.getScore().get()).isEqualTo(Score.of(24));
    }
}
