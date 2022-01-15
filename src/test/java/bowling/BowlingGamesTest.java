package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    @DisplayName("참가자별 볼링 게임을 생성한다.")
    @Test
    void 볼링_게임_생성() {
        // given
        BowlingGames bowlingGames = new BowlingGames();

        // when
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));

        // then
        assertThat(bowlingGames.numberOfPlayers()).isEqualTo(2);
    }

    @DisplayName("게임 시작 시 첫 번째 플에이어가 턴이이어야 한다.")
    @Test
    void 첫_번째_플레이어_턴_확인() {
        // given
        BowlingGames bowlingGames = new BowlingGames();
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));

        // when
        BowlingGame bowlingGameTurn = bowlingGames.currentTurn();

        // then
        assertThat(bowlingGameTurn.getPlayerName()).isEqualTo("DUGI");
        assertThat(bowlingGameTurn.recentFrameNo()).isEqualTo(1);
    }

    @DisplayName("1게임 1프레임 스트라이크 시 2게임 1프레임 차례")
    @Test
    void 다음_참가자_차례_확인() {
        // given
        BowlingGames bowlingGames = new BowlingGames();
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));

        BowlingGame bowlingGameTurn1 = bowlingGames.currentTurn();
        bowlingGameTurn1.bowl(new Pins(10));

        // when
        BowlingGame bowlingGameTurn2 = bowlingGames.currentTurn();
//
//        // then
        assertThat(bowlingGameTurn2.getPlayerName()).isEqualTo("MANSE");
        assertThat(bowlingGameTurn2.recentFrameNo()).isEqualTo(1);
    }

    @Test
    void 한_사람이라도_게임_진행_중이면_게임_진행() {
        BowlingGames bowlingGames = new BowlingGames();
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));

        BowlingGame bowlingGameTurn1 = bowlingGames.currentTurn();
        gameOverBowl(bowlingGameTurn1);

        // when
        boolean isNextGame = bowlingGames.hasNextGame();

        // then
        assertThat(isNextGame).isTrue();
    }

    @Test
    void 모든_참가자_게임_종료() {
        BowlingGames bowlingGames = new BowlingGames();
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));

        BowlingGame bowlingGameTurn1 = bowlingGames.currentTurn();
        gameOverBowl(bowlingGameTurn1);

        BowlingGame bowlingGameTurn2 = bowlingGames.currentTurn();
        gameOverBowl(bowlingGameTurn2);

        // when
        boolean isNextGame = bowlingGames.hasNextGame();

        // then
        assertThat(isNextGame).isFalse();
    }

    @Test
    void 턴인_참가자_이름() {
        // given
        BowlingGames bowlingGames = new BowlingGames();
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));

        bowlingGames.bowl(new Pins(10));

        // when
        String turnPlayerName = bowlingGames.getTurnPlayerName();


        // then
        assertThat(turnPlayerName).isEqualTo("MANSE");
    }

    @Test
    void 굴리기_스트라이크_시_다음_턴_확인() {
        // given
        BowlingGames bowlingGames = new BowlingGames();
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));

        bowlingGames.bowl(new Pins(10));
        bowlingGames.bowl(new Pins(10));
        bowlingGames.bowl(new Pins(10));

        // when
        BowlingGame bowlingGameTurn = bowlingGames.currentTurn();

        // then
        assertThat(bowlingGameTurn.recentFrameNo()).isEqualTo(2);
        assertThat(bowlingGameTurn.getPlayerName()).isEqualTo("MANSE");
    }

    @Test
    void 세명_일_경우_턴_확인() {
        // given
        BowlingGames bowlingGames = new BowlingGames();
        bowlingGames.enter(new Player("dugi"));
        bowlingGames.enter(new Player("manse"));
        bowlingGames.enter(new Player("aaa"));

        bowlingGames.bowl(new Pins(10));
        bowlingGames.bowl(new Pins(10));

        // when
        BowlingGame bowlingGameTurn = bowlingGames.currentTurn();

        // then
        assertThat(bowlingGameTurn.recentFrameNo()).isEqualTo(1);
        assertThat(bowlingGameTurn.getPlayerName()).isEqualTo("AAA");
    }

    private void gameOverBowl(BowlingGame bowlingGameTurn1) {
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));

        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
        bowlingGameTurn1.bowl(new Pins(10));
    }
}