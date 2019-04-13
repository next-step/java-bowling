package bowling.game;

import bowling.player.Player;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    private static final Logger log = LoggerFactory.getLogger(BowlingGameTest.class);

    @Test
    public void BowlingGame이_끝났는지_확인() {
        // given
        Player player = new Player("SYC");
        BowlingGame bowlingGame = new BowlingGame(player);

        // when
        for (int i = 0; i < 11; i++) {
            bowlingGame.pitch(PinScore.TEN);
        }

        // then
        assertThat(bowlingGame.isEnd()).isTrue();
    }

    @Test
    public void 시작_전_toString() {
        // given
        Player player = new Player("SYC");

        // when
        BowlingGame bowlingGame = new BowlingGame(player);

        // then
        assertThat(bowlingGame.toString()).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n" +
                "|  SYC |      |      |      |      |      |      |      |      |      |      |");

        log.debug("BowlingGame\n{}", bowlingGame);
    }

    @Test
    public void 투구_후_toString() {
        // given
        Player player = new Player("SYC");
        BowlingGame bowlingGame = new BowlingGame(player);

        // when
        bowlingGame.pitch(PinScore.THREE);
        bowlingGame.pitch(PinScore.ZERO);
        bowlingGame.pitch(PinScore.TEN);
        bowlingGame.pitch(PinScore.TEN);

        // then
        assertThat(bowlingGame.toString()).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n" +
                "|  SYC |  3|- |  X   |  X   |      |      |      |      |      |      |      |");

        log.debug("BowlingGame\n{}", bowlingGame);
    }

    @Test
    public void 올_스트라이크_toString() {
        // given
        Player player = new Player("SYC");
        BowlingGame bowlingGame = new BowlingGame(player);

        // when
        while (!bowlingGame.isEnd()) {
            bowlingGame.pitch(PinScore.TEN);
        }

        // then
        assertThat(bowlingGame.toString()).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n" +
                "|  SYC |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X |");

        log.debug("BowlingGame\n{}", bowlingGame);
    }
}
