package bowling.domain;

import bowling.domain.frames.FrameImpl;
import bowling.domain.frames.LastFrame;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {
    @Test
    @DisplayName("첫번째 투구에서 10개의 핀을 모두 쓰러트리면 스트라이크(X)")
    public void strikeTest() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame.getPitchings()).containsExactly(Pitching.STRIKE);
    }

    @Test
    @DisplayName("한 프레임의 모든 투구에서 10개의 핀을 모두 쓰러트리지 못한 경우 점수만 표기")
    public void scoreTest() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.getPitchings()).containsExactly(Pitching.THREE_PINS, Pitching.FIVE_PINS);
    }

    @Test
    @DisplayName("한 프레임의 두번째 투구에서 10개의 핀을 모두 쓰러트린 경우 스페어(/)")
    public void spareTest() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(7));

        assertThat(frame.getPitchings()).containsExactly(Pitching.THREE_PINS, Pitching.SPARE);
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-)")
    public void gutterTest_secondPitching() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(0));

        assertThat(frame.getPitchings()).containsExactly(Pitching.THREE_PINS, Pitching.GUTTER);
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-)")
    public void gutterTest_thirdPitching() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(7));
        frame.setKnockDownPins(KnockDownPins.valueOf(0));

        assertThat(frame.getPitchings()).containsExactly(Pitching.THREE_PINS, Pitching.SPARE, Pitching.GUTTER);
    }

    @Test
    @DisplayName("3번째 투구에서 스페어가 나오는 경우")
    public void lastPitchingSpareTest() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(10));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.getPitchings()).containsExactly(Pitching.STRIKE, Pitching.FIVE_PINS, Pitching.SPARE);
    }

    @Test
    @DisplayName("첫번째 투구에서 스트라이크를 했다면 프레임 계속 진행")
    public void frameEndTestStrike() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(10));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("두번째 투구에서 스페어를 했다면 프레임 계속 진행")
    public void frameEndTestSpare() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(5));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("첫번째 두번째 투구에서 스트라이크나 스페어를 하지 못하면 프레임 종료")
    public void frameEndTest() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(3));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("세번째 투구까지 한다면 프레임 종료")
    public void frameEndThird() {
        FrameImpl frame = LastFrame.of(10, null);
        frame.setKnockDownPins(KnockDownPins.valueOf(5));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));
        frame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame.isEnd()).isTrue();
    }
}
