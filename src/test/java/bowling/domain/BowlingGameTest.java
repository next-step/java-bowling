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

        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2), Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"))));

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


        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2), Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"))));

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


        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2), Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"))));

        assertThat(bowlingGame.isRoundEnd()).isFalse();
    }

    @DisplayName("Bowling 게임에서 frames 들 저장 되는지 테스트(Strike 3번 이면 프레임이 3번 지났음으로 사이즈가 3)")
    @Test
    void bowl() {
        Frames frames1 = Frames.newInstance();

        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1), Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"))));

        bowlingGame.bowl(Player.from("syd"), 10);

        bowlingGame.bowl(Player.from("syd"), 10);

        bowlingGame.bowl(Player.from("syd"), 10);

        assertThat(bowlingGame.currentGame(Player.from("syd")).size()).isEqualTo(3);
    }

    @DisplayName("2경기 중 1번째 경기의 프레임이 종료 되면, 2번째 경기는 한번 더 쳐야 하기 때문에 true를 반환")
    @Test
    void isBowlingTest() {
        Frames frames1 = Frames.newInstance();
        Frames frames2 = Frames.newInstance();

        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2), Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"))));

        bowlingGame.bowl(Player.from("syd"), 10);

        bowlingGame.bowl(Player.from("kjy"), 3);

        assertThat(bowlingGame.isBowling(Player.from("kjy"))).isTrue();
    }

    @DisplayName("2경기 중 1번째 경기의 프레임이 종료 되면 false 를 반환")
    @Test
    void isBowlingTest2() {
        Frames frames1 = Frames.newInstance();
        Frames frames2 = Frames.newInstance();

        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList(frames1, frames2), Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"))));

        bowlingGame.bowl(Player.from("syd"), 10);

        bowlingGame.bowl(Player.from("kjy"), 3);

        assertThat(bowlingGame.isBowling(Player.from("syd"))).isFalse();
    }
}
