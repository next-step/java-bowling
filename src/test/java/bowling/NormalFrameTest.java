package bowling;

import bowling.domain.FrameStrategy;
import bowling.domain.NormalFrame;
import bowling.domain.PinNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {

    @Test
    @DisplayName("첫 번째 투구가 스트라이크인 경우 두 번째 투구는 없다.")
    void canKnowSecondExist() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.play(new PinNumber(10));

        Assertions.assertThat(normalFrame.hasSecond()).isEqualTo(false);
    }

    @Test
    @DisplayName("현재 프레임이 스트라이크나 스페어가 아닌 경우 현재 프레임 내에서 점수가 결정된다.")
    void scoreInFrameWhenIsStrikeNorSpare() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.play(new PinNumber(3));
        normalFrame.play(new PinNumber(5));

        Assertions.assertThat(normalFrame.score()).isEqualTo(8);
    }

    @Test
    @DisplayName("현재 프레임이 스페어인 경우 다음 프레임의 첫 번째 투구 점수까지 반영된다.")
    void scoreInFrameWhenIsSpare() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.play(new PinNumber(3));
        normalFrame.play(new PinNumber(7));

        int frameNumber = 1;
        FrameStrategy nextFrame = normalFrame.newNextFrame(frameNumber);
        nextFrame.play(new PinNumber(8));
        nextFrame.play(new PinNumber(2));

        Assertions.assertThat(normalFrame.score()).isEqualTo(18);
    }

    @Test
    @DisplayName("현재 프레임이 스트라이크인 경우 다음 프레임의 두 번째 투구 점수까지 반영된다.")
    void scoreInFrameWhenIsStrike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.play(new PinNumber(10));

        int frameNumber = 1;
        FrameStrategy nextFrame = normalFrame.newNextFrame(frameNumber);
        nextFrame.play(new PinNumber(8));
        nextFrame.play(new PinNumber(2));

        Assertions.assertThat(normalFrame.score()).isEqualTo(20);
    }

    @Test
    @DisplayName("스트라이크가 연속 두 번 나오면 세 번째 프레임의 첫 번째 투구 점수까지 반영된다.")
    void scoreInFrameWhenIsTwoStrike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.play(new PinNumber(10));
        int frameNumber = 1;
        FrameStrategy nextFrame = normalFrame.newNextFrame(frameNumber);
        nextFrame.play(new PinNumber(10));

        frameNumber = 2;
        nextFrame = nextFrame.newNextFrame(frameNumber);
        nextFrame.play(new PinNumber(5));
        nextFrame.play(new PinNumber(5));

        Assertions.assertThat(normalFrame.score()).isEqualTo(25);
    }
}
