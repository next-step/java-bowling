package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {

    @DisplayName("볼링게임이 끝나지 않은 상태면 false 를 반환")
    @Test
    void bowlingGameIsNotEnd() {
        Frames frames1 = Frames.newInstance();
        Frames frames2 = Frames.newInstance();

        Frame finishFinalFrame = new FinalFrame();

        finishFinalFrame.bowl(Pins.of(5));
        finishFinalFrame.bowl(Pins.of(3));

        Frame notFinishFinalFrame = new FinalFrame();

        notFinishFinalFrame.bowl(Pins.of(10));
        notFinishFinalFrame.bowl(Pins.of(10));

        frames1.add(finishFinalFrame);
        frames2.add(notFinishFinalFrame);


        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2));

        assertThat(bowlingGame.isEnd()).isFalse();
    }

    @DisplayName("볼링게임이 끝나지 않은 상태면 true 를 반환")
    @Test
    void bowlingGameIsAllEnd() {
        Frames frames1 = Frames.newInstance();
        Frames frames2 = Frames.newInstance();

        Frame finishFinalFrame = new FinalFrame();

        finishFinalFrame.bowl(Pins.of(5));
        finishFinalFrame.bowl(Pins.of(3));

        Frame notFinishFinalFrame = new FinalFrame();

        notFinishFinalFrame.bowl(Pins.of(10));
        notFinishFinalFrame.bowl(Pins.of(10));
        notFinishFinalFrame.bowl(Pins.of(10));
        frames1.add(finishFinalFrame);
        frames2.add(notFinishFinalFrame);


        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2));

        assertThat(bowlingGame.isEnd()).isTrue();
    }

    @DisplayName("볼링 한 라운드가 끝나지 않은 상태면 false 를 반환")
    @Test
    void isRoundEnd() {
        Frames frames1 = Frames.newInstance();
        Frames frames2 = Frames.newInstance();

        Frame normalFrame = new NormalFrame(1);

        normalFrame.bowl(Pins.of(10));

        Frame normalFrame2 = new NormalFrame(1);

        normalFrame2.bowl(Pins.of(2));

        frames1.add(normalFrame);
        frames2.add(normalFrame2);


        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2));

        assertThat(bowlingGame.isRoundEnd(1)).isFalse();
    }
}
