package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10), new Pinfall(10));
        finalFrame.roll(new Pinfall(10));

        BowlingGame finishedBowlingGame = new BowlingGame(new Player("111"), finalFrame);
        BowlingGames bowlingGames = new BowlingGames(Arrays.asList(finishedBowlingGame, finishedBowlingGame));

        assertThat(bowlingGames.isDone()).isTrue();
    }

    @Test
    @DisplayName("스트라이크이면 현제 Player는 다음 Player")
    void Given_StrikeRoll_When_CurrentPlayer_Then_PlayerChanged() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Arrays.asList(new Player("111"), new Player("222"))));
        bowlingGames.roll(new Pinfall(10));

        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("222"));
    }

    @Test
    @DisplayName("모든 Player가 한 프레임씩 공을 굴렸으면 다시 첫 번째 Player 차례")
    void Given_StrikeRollTwice_When_CurrentPlayer_Then_PlayerChanged() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Arrays.asList(new Player("111"), new Player("222"))));
        bowlingGames.roll(new Pinfall(10));
        bowlingGames.roll(new Pinfall(10));

        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("111"));
    }

    @Test
    @DisplayName("1개 핀만 쓰러뜨렸으면 Player 변경 X")
    void Given_OnePinfall_When_CurrentPlayer_Then_PlayerNOTChanged() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Arrays.asList(new Player("111"), new Player("222"))));
        bowlingGames.roll(new Pinfall(1));

        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("111"));
    }
}
