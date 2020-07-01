package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    Frames frames = new Frames();
    NormalFrame normalFrame = new NormalFrame();

    @DisplayName("일반 볼 테스트")
    @Test
    public void normalFrameTest() {

        int next = normalFrame.addScore(4, frames);
        next = normalFrame.addScore(4, frames);

        assertThat(normalFrame.isStrike()).isFalse();
        assertThat(normalFrame.isSpare()).isFalse();
        assertThat(normalFrame.isMiss()).isFalse();
        assertThat(next).isEqualTo(1);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void normalFrameStrikeTest() {
        int next = normalFrame.addScore(10, frames);

        assertThat(normalFrame.isStrike()).isTrue();
        assertThat(next).isEqualTo(1);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void normalFrameSpareTest() {
        int next = normalFrame.addScore(3, frames);
        next = normalFrame.addScore(7, frames);

        assertThat(normalFrame.isStrike()).isFalse();
        assertThat(normalFrame.isSpare()).isTrue();
        assertThat(next).isEqualTo(1);

    }

    @DisplayName("miss 테스트")
    @Test
    public void normalFrameMissTest() {
        int next = normalFrame.addScore(0, frames);

        assertThat(normalFrame.isStrike()).isFalse();
        assertThat(normalFrame.isSpare()).isFalse();
        assertThat(normalFrame.isMiss()).isTrue();
        assertThat(next).isEqualTo(0);
    }

    @DisplayName("miss 테스트")
    @Test
    public void getFrameScoreTest() {
        normalFrame.addScore(10, frames);
        normalFrame.addScore(5, frames);
        System.out.println(normalFrame.getScore(frames));
    }
}
