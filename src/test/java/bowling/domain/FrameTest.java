package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FrameTest {

    @Test
    void 일반프레임_종료판단_2회투구() {
        Frame frame = Frame.createNormal();

        frame.bowling(5);
        assertThat(frame.isEnd()).isFalse();

        frame.bowling(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 일반프레임_종료판단_스트라이크() {
        Frame frame = Frame.createNormal();
        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 마지막프레임_종료판단_3회투구() {
        Frame frame = Frame.createFinal();
        frame.bowling(10);
        frame.bowling(10);
        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 프레임종료_예외() {
        Frame frame = Frame.createFinal();
        frame.bowling(10);
        frame.bowling(10);
        frame.bowling(10);

        assertThatIllegalStateException().isThrownBy(() -> frame.bowling(9));
    }

    @Test
    void 프레임종료_예외2() {
        Frame frame = Frame.createNormal();
        frame.bowling(5);
        frame.bowling(5);
        assertThat(frame.isEnd()).isTrue();

        assertThatIllegalStateException().isThrownBy(() -> frame.bowling(9));
    }

    @Test
    void 다음프레임생성() {
        Frame first = Frame.createNormal();
        first.bowling(2);
        first.bowling(2);
        Frame next = first.createNext();

        assertThat(first.getScore()).isEqualTo(next.getBeforeScore());
    }
    
    // ==============================================================================

    @Test
    void 노말점수_계산_미스() {
        Frame frame = Frame.createNormal();
        frame.bowling(2);
        frame.bowling(2);

        assertThat(frame.getScore()).isEqualTo(Score.of(4));
    }

    @Test
    void 노말점수_계산_스트라이크() {
        Frame first = Frame.createNormal();
        first.bowling(2);
        first.bowling(2);

        Frame next = first.createNext();
        next.bowling(10);

        Frame next1 = next.createNext();
        next1.bowling(10);

        Frame next2 = next1.createNext();
        next2.bowling(2);

        assertThat(next.getScore()).isEqualTo(Score.of(26));
    }

    @Test
    void 노말점수_계산_스페어() {
        Frame first = Frame.createNormal();
        first.bowling(2);
        first.bowling(2);

        Frame next = first.createNext();
        next.bowling(5);
        next.bowling(5);

        Frame nextNext = next.createNext();
        nextNext.bowling(2);
        nextNext.bowling(2);

        assertThat(next.getScore()).isEqualTo(Score.of(16));
    }

    @Test
    void 마지막점수_계산_미스() {
        Frame first = Frame.createNormal();
        first.bowling(2);
        first.bowling(2);

        Frame finalFrame = first.createFinal2();
        finalFrame.bowling(2);
        finalFrame.bowling(2);
        
        assertThat(finalFrame.getScore()).isEqualTo(Score.of(8));
    }

    @Test
    void 마지막점수_계산_3스트라이크() {
        Frame first = Frame.createNormal();
        first.bowling(2);
        first.bowling(2);

        Frame finalFrame = first.createFinal2();
        finalFrame.bowling(10); // 4 + 10 + 10 + 10 = 34
        finalFrame.bowling(10); // 34 + 10 + 10 = 54
        finalFrame.bowling(10); // 54 + 10 = 64

        assertThat(finalFrame.getScore()).isEqualTo(Score.of(64));
    }

    @Test
    void 마지막점수_계산_스페어() {
        Frame first = Frame.createNormal();
        first.bowling(2);
        first.bowling(2);

        Frame finalFrame = first.createFinal2();
        finalFrame.bowling(5); // 5
        finalFrame.bowling(5); // 15
        finalFrame.bowling(10); // 10

        assertThat(finalFrame.getScore()).isEqualTo(Score.of(34));
    }
}
