package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingGameTest {

    private BowlingGame finishedGame;
    private BowlingGame initiatedGame;

    @BeforeEach
    void setUp() {
        initiatedGame = new BowlingGame(Players.of(Arrays.asList("aaa", "bbb")));
        finishedGame = new BowlingGame(Players.of(Arrays.asList("aaa", "bbb")));

        for (int i = 0; i < 24; ++i) {
            finishedGame.roll(RollResult.of(10));
        }
    }

    @Test
    @DisplayName("다음에 투구할 플레이어의 이름을 가져온다.")
    void getNameOfPlayerWhoPlaysNext() {
        assertThat(initiatedGame.getNameOfCurrentPlayer()).isEqualTo("aaa");

        initiatedGame.roll(RollResult.of(10));

        assertThat(initiatedGame.getNameOfCurrentPlayer()).isEqualTo("bbb");
    }

    @Test
    @DisplayName("다음 프레임으로 넘어가면 다시 처음 플레이어의 이름을 가져온다.")
    void returnToFirstPlayer() {
        initiatedGame.roll(RollResult.of(10));
        initiatedGame.roll(RollResult.of(10));

        assertThat(initiatedGame.getNameOfCurrentPlayer()).isEqualTo("aaa");
    }

    @Test
    @DisplayName("게임이 종료된 상태에서 이름을 가져오려고 시도하면 예외 처리한다.")
    void throwExceptionWhenTryToGetPlayerNameIfGameIsEnded() {
        assertThatThrownBy(() -> finishedGame.getNameOfCurrentPlayer()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("게임이 종료되면 true 를 반환한다.")
    void returnTrueIfGameEnded() {
        assertThat(finishedGame.isFinished()).isTrue();
    }
    
}
