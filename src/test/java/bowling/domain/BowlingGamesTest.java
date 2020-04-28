package bowling.domain;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BowlingGamesTest {

    @DisplayName("Players를 이용해 BowlingGames를 생성한다")
    @Test
    public void findGameByPlayer_success() throws Exception {
        Players players = new Players(
                Arrays.asList(new Player("aaa"), new Player("bbb")));
        BowlingGames games = BowlingGames.from(players);
    }

    @DisplayName("Player의 게임을 진행할 수 있다")
    @Test
    public void playPlayerGame_success() throws Exception {
        //given
        Player playerA = new Player("aaa");
        Players players = new Players(Arrays.asList(playerA));
        BowlingGames games = BowlingGames.from(players);

        BowlingGame game = new BowlingGame(playerA);
        game.play(2);
        BowlingGames compare = new BowlingGames(Arrays.asList(game));

        //when
        games.playPlayerGame(playerA, 2);

        //then
        assertTrue(games.equals(compare));
    }

    @DisplayName("해당 프레임이 더 투구 가능한지 확인한다 : 추가 투구 불가능")
    @Test
    public void isMoreThrowable_success_not() throws Exception {
        //given
        Player playerA = new Player("aaa");
        Players players = new Players(Arrays.asList(playerA));
        BowlingGames games = BowlingGames.from(players);
        games.playPlayerGame(playerA, 10);

        //when
        boolean result = games.isMoreThrowable(playerA, 1);

        //then
        assertFalse(result);
    }

    @DisplayName("해당 프레임이 더 투구 가능한지 확인한다 : 추가 투구 가능")
    @Test
    public void isMoreThrowable_success_throwable() throws Exception {
        //given
        Player playerA = new Player("aaa");
        Players players = new Players(Arrays.asList(playerA));
        BowlingGames games = BowlingGames.from(players);
        games.playPlayerGame(playerA, 2);

        //when
        boolean result = games.isMoreThrowable(playerA, 1);

        //then
        assertTrue(result);
    }

    @DisplayName("모든 플레이어가 현재 프레임이 마무리 되었는지 확인한다")
    @Test
    public void isAllPlayerLastFrameFinish_success() throws Exception {
        //given
        Player playerA = new Player("aaa");
        Player playerB = new Player("bbb");

        Players players = new Players(Arrays.asList(playerA, playerB));
        BowlingGames games = BowlingGames.from(players);
        games.playPlayerGame(playerA, 10);
        games.playPlayerGame(playerB, 10);

        //when
        boolean result = games.isAllPlayerLastFrameFinish(1);

        //then
        assertTrue(result);
    }

    @DisplayName("모든 플레이어가 현재 프레임이 마무리 되었는지 확인한다")
    @Test
    public void isAllPlayerLastFrameFinish_success_false() throws Exception {
        //given
        Player playerA = new Player("aaa");
        Player playerB = new Player("bbb");

        Players players = new Players(Arrays.asList(playerA, playerB));
        BowlingGames games = BowlingGames.from(players);
        games.playPlayerGame(playerA, 10);
        games.playPlayerGame(playerB, 5);

        //when
        boolean result = games.isAllPlayerLastFrameFinish(1);

        //then
        assertFalse(result);
    }
}
