package bowling.model.game;

import bowling.model.frame.FinalFrame;
import bowling.model.frame.Frame;
import bowling.model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("볼링 게임 컬렉션 테스트")
public class BowlingGamesTest {
    private static final List<Player> PLAYERS
            = Arrays.asList(new Player(1, "ABC"), new Player(2, "DEF"));

    @DisplayName("볼링 게임 생성시, 플레이어 컬렉션이 비어있으면 예외가 발생한다.")
    @Test
    void emptyPlayersExceptionTest() {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BowlingGames(null))
                .withMessage("볼링 게임 진행을 위해서는 적어도 1명의 플레이어가 필요합니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BowlingGames(new ArrayList<>()))
                .withMessage("볼링 게임 진행을 위해서는 적어도 1명의 플레이어가 필요합니다.");
    }

    @DisplayName("볼링 게임 생성시, 겹치는 플레이어 번호가 존재하면 예외가 발생한다.")
    @Test
    void duplicatedPlayerNumberExceptionTest() {
        // given
        List<Player> players = Arrays.asList(new Player(1, "ABC"), new Player(1, "DEF"));

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BowlingGames(players))
                .withMessage("중복되는 플레이어 번호가 존재합니다.");

    }

    @DisplayName("볼링 게임 생성시 주입받은 첫 번째 플레이어가 게임의 첫 번째 차례를 가진다.")
    @Test
    void createBowlingGamesTest() {
        // when, then
        assertEquals(new BowlingGames(PLAYERS).currentPlayerName(), "ABC");
    }

    @DisplayName("볼링 게임의 마지막 차례의 마지막 FinalFrame이 진행 완료된 이후로는 다음 게임을 진행할 수 없다.")
    @Test
    void finalFrameOfLastTurnCannotPlayNextTest() {
        // given
        int finalFrameNumber = 10;
        int firstFallenPinCount = 5;
        int secondFallenPinCount = 4;
        int score = 9;
        int remainingPitchingCount = 0;
        int bonusFallenPinCount = 0;

        List<Frame> frames = Arrays.asList(new FinalFrame(finalFrameNumber, firstFallenPinCount, secondFallenPinCount,
                score, remainingPitchingCount, bonusFallenPinCount));
        BowlingGames bowlingGames = new BowlingGames(PLAYERS, frames);

        // when, then
        assertFalse(bowlingGames.canPlayNext());
    }

    @DisplayName("한 프레임을 모두 진행 완료한 후에 다음 플레이어로 차례가 넘어간다.")
    @Test
    void nextTurnTest() {
        // given
        BowlingGames bowlingGames = new BowlingGames(PLAYERS);

        // when, then
        assertEquals(bowlingGames.currentPlayerName(), "ABC");

        bowlingGames.play(5);
        assertEquals(bowlingGames.currentPlayerName(), "ABC");

        bowlingGames.play(4); // ABC, 미스(두 번 투구)
        assertEquals(bowlingGames.currentPlayerName(), "DEF");

        bowlingGames.play(5);
        assertEquals(bowlingGames.currentPlayerName(), "DEF");

        bowlingGames.play(5); // DEF, 스페어(두 번 투구)
        assertEquals(bowlingGames.currentPlayerName(), "ABC");

        bowlingGames.play(10); // ABC, 스트라이크(한 번 투구)
        assertEquals(bowlingGames.currentPlayerName(), "DEF");

        bowlingGames.play(10); // DEF, 스트라이크(한 번 투구)
        assertEquals(bowlingGames.currentPlayerName(), "ABC");
    }
}
