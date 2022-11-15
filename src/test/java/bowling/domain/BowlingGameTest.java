package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingGameTest {


    @Test
    void shouldValidateDuplicateUsernames() {
        assertThatThrownBy(() -> new BowlingGame(List.of(new Username("kcs"), new Username("kcs"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("현재 차례의 사용자를 반환해야 합니다.")
    void shouldReturnCurrentPlayingUser() {
        BowlingGame bowlingGame = new BowlingGame(List.of(new Username("abc"), new Username("kcs")));

        Username firstUser = bowlingGame.currentPlayerName();
        bowlingGame.play(10);
        Username secondUser = bowlingGame.currentPlayerName();

        assertThat(firstUser).isEqualTo(new Username("abc"));
        assertThat(secondUser).isEqualTo(new Username("kcs"));
    }

    @Test
    @DisplayName("사용자별로 게임을 시도합니다.")
    void shouldPlayGameByUser() {
        BowlingGame bowlingGame = new BowlingGame(List.of(new Username("abc"), new Username("kcs")));

        ScoreResult firstResult = bowlingGame.play(10);
        ScoreResult secondResult = bowlingGame.play(10);

        assertThat(firstResult.isSameUsername(new Username("abc")));
        assertThat(secondResult.isSameUsername(new Username("kcs")));
    }
}
