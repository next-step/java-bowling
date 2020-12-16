package bowling.domain;

import bowling.domain.frames.FrameImpl;
import bowling.domain.frames.NormalFrame;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;


public class NormalFrameTest {
    @Test
    @DisplayName("첫번째 투구에서 10개의 핀을 모두 쓰러트리면 스트라이크(X)")
    public void strikeTest() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame.getPitchings()).containsExactly(Pitching.STRIKE);
    }

    @Test
    @DisplayName("한 프레임의 모든 투구에서 10개의 핀을 모두 쓰러트리지 못한 경우 점수만 표기")
    public void scoreTest() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.getPitchings()).containsExactly(Pitching.THREE_PINS, Pitching.FIVE_PINS);
    }

    @Test
    @DisplayName("한 프레임의 두번째 투구에서 10개의 핀을 모두 쓰러트린 경우 스페어(/)")
    public void spareTest() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(7));

        assertThat(frame.getPitchings()).containsExactly(Pitching.THREE_PINS, Pitching.SPARE);
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-)")
    public void gutterTest_firstPitcing() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(0));

        assertThat(frame.getPitchings()).containsExactly(Pitching.GUTTER);
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-)")
    public void gutterTest_secondPitcing() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(0));

        assertThat(frame.getPitchings()).containsExactly(Pitching.THREE_PINS, Pitching.GUTTER);
    }

    @Test
    @DisplayName("첫번째 투구에서 스트라이크면 프레임 종료")
    public void frameEndTestStrike() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("두번 투구시에 프레임 종료")
    public void frameEndTest() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스타라이크가 아닌 첫번째 투구시에 프레임 계속 진행")
    public void frameEndNotStrike() {
        FrameImpl frame = NormalFrame.getFirstFrame();
        frame.setKnockDownPins(KnockDownPins.valueOf(3));

        assertThat(frame.isEnd()).isFalse();
    }
}
