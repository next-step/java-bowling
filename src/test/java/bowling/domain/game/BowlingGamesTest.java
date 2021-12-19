package bowling.domain.game;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Round;
import bowling.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    private BowlingGames bowlingGames;

    @BeforeEach
    void setUp() {
        bowlingGames = BowlingGames.fromWithUserNames(Arrays.asList("miz", "tiz"));
    }

    @Test
    void createTest() {
        assertThat(BowlingGames.fromWithUserNames(Arrays.asList("miz", "tiz")))
                .isEqualTo(BowlingGames.from(Arrays.asList(
                        BowlingGame.readyGame(User.from("miz")),
                        BowlingGame.readyGame(User.from("tiz"))
                )));
    }

    @DisplayName("nowTurnUserName() 던질 user의 이름을 반환한다.")
    @Test
    void nowTurnUserTest() {
        assertThat(bowlingGames.nowTurnUserName()).isEqualTo("miz");
    }

    @DisplayName("bowlNowTurn() 현재 턴의 게임의 bowl 을 호출 한다.")
    @Test
    void bowlNowTurnTest() {
        //when
        bowlingGames.bowlNowTurn(Pin.from(5));
        bowlingGames.bowlNowTurn(Pin.from(4));
        bowlingGames.bowlNowTurn(Pin.from(6));
        bowlingGames.bowlNowTurn(Pin.from(4));
        bowlingGames.bowlNowTurn(Pin.from(4));

        //then
        BowlingGame miz = BowlingGame.readyGame(User.from("miz"));
        BowlingGame tiz = BowlingGame.readyGame(User.from("tiz"));

        miz.bowl(Pin.from(5));
        miz.bowl(Pin.from(4));
        miz.bowl(Pin.from(4));

        tiz.bowl(Pin.from(6));
        tiz.bowl(Pin.from(4));

        assertThat(bowlingGames).isEqualTo(
                BowlingGames.of(Arrays.asList(miz, tiz), Round.from(2))
        );
    }
}
