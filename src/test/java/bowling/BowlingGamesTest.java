package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BowlingGamesTest {
    @Test
    @DisplayName("초기 상태일 때 현재 Player는 첫 번째 Player")
    void When_CurrentPlayer_Then_ReturnFirstPlayer() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Arrays.asList(new Player("111"), new Player("222"))));
        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("111"));
    }

    @Test
    @DisplayName("모든 볼링게임이 끝났으면 isDone은 True")
    void Given_FinishedBowlingGames_When_IsDone_Then_True() {
        FinalFrame finalFrame = new FinalFrame(Pinfall.createStrike(), Pinfall.createStrike());
        finalFrame.roll(Pinfall.createStrike());

        BowlingGame finishedBowlingGame = new BowlingGame(new Player("111"), finalFrame);
        BowlingGames bowlingGames = new BowlingGames(new FrameNumber(10), Arrays.asList(finishedBowlingGame, finishedBowlingGame));

        assertThat(bowlingGames.isDone()).isTrue();
    }

    @Test
    @DisplayName("스트라이크이면 현제 Player는 다음 Player")
    void Given_StrikeRoll_When_CurrentPlayer_Then_PlayerChanged() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Arrays.asList(new Player("111"), new Player("222"))));
        bowlingGames.roll(Pinfall.createStrike());

        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("222"));
    }

    @Test
    @DisplayName("모든 Player가 한 프레임씩 공을 굴렸으면 다시 첫 번째 Player 차례")
    void Given_StrikeRollTwice_When_CurrentPlayer_Then_PlayerChanged() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Arrays.asList(new Player("111"), new Player("222"))));
        bowlingGames.roll(Pinfall.createStrike());
        bowlingGames.roll(Pinfall.createStrike());

        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("111"));
    }

    @Test
    @DisplayName("1개 핀만 쓰러뜨렸으면 Player 변경 X")
    void Given_OnePinfall_When_CurrentPlayer_Then_PlayerNOTChanged() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Arrays.asList(new Player("111"), new Player("222"))));
        bowlingGames.roll(Pinfall.create(1));

        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("111"));
    }

    @Test
    @DisplayName("10번 프레임일 때 마지막 플레이어까지 공을 던져야 한다 ")
    void Given_10thFrame_When_Roll_NoException() {
        FinalFrame finalFrame = new FinalFrame(Pinfall.createStrike(), Pinfall.createStrike());
        FinalFrame finalFrame1 = new FinalFrame();
        BowlingGame bowlingGame = new BowlingGame(new Player("111"), finalFrame);
        BowlingGame bowlingGame2 = new BowlingGame(new Player("222"), finalFrame1);
        BowlingGames bowlingGames = new BowlingGames(new FrameNumber(10), Arrays.asList(bowlingGame, bowlingGame2));

        bowlingGames.roll(Pinfall.create(0));
        assertDoesNotThrow(() -> bowlingGames.roll(Pinfall.createGutter()));
    }
}
