package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BowlingMatchTest {

    private static final int INDEX_OF_FIRST_PLAYER = 0;
    private static final int INDEX_OF_SECOND_PLAYER = 1;
    private List<Player> players;
    private BowlingMatch bowlingMatch;

    @BeforeEach
    void setUp() {
        Player player1 = Player.from("hjs");
        Player player2 = Player.from("pje");
        Player player3 = Player.from("jek");
        players = new ArrayList<>(Arrays.asList(player1, player2, player3));
        bowlingMatch = BowlingMatch.start(players);
    }

    @Test
    void 플레이어들을_입력하여_볼링경기를_생성한다() {
        assertThat(bowlingMatch).isNotNull();
    }

    @Test
    void 현재_플레이중인_플레이어를_반환한다() {
        //when
        BowlingGame bowlingGame = bowlingMatch.getOngoingGame();

        //then
        assertThat(bowlingGame.getPlayer())
                .isEqualTo(players.get(INDEX_OF_FIRST_PLAYER));
    }

    @Test
    void 현재_플레이중인_프레임이_종료되면_다음_플레이어가_플레이한다() {
        //when
        BowlingGame bowlingGame = bowlingMatch.getOngoingGame();
        bowlingGame.play(Pins.STRIKE);

        BowlingGame ongoingGame = bowlingMatch.getOngoingGame();

        //then
        assertAll(
                () -> assertThat(bowlingGame.getFirstFrame().getState().isClosed()).isTrue(),
                () -> assertThat(ongoingGame.getPlayer()).isEqualTo(players.get(INDEX_OF_SECOND_PLAYER))
        );
    }

    @Test
    void 모든_볼링게임이_종료되면_볼링경기가_종료된다() {
        //given
        int numberOfPlaysForPerfectGame = players.size() * 12;

        //when
        for (int i = 0; i < numberOfPlaysForPerfectGame; i++) {
            BowlingGame ongoingGame = bowlingMatch.getOngoingGame();
            ongoingGame.play(Pins.STRIKE);
        }

        //then
        assertThat(bowlingMatch.isOver()).isTrue();
    }
}
