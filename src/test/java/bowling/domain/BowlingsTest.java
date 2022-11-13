package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingsTest {

    private static final int BONUS_ROUND = 2;

    @Test
    @DisplayName("모든 사용자의 게임이 끝나야 true를 반환해야합니다.")
    void shouldReturnWhetherGameFinish() {
        Bowlings bowlings = new Bowlings(List.of(new Username("abc"), new Username("kcs")));

        playUntilGameFinish(bowlings, 2);

        assertThat(bowlings.isFinish()).isTrue();
    }

    private void playUntilGameFinish(Bowlings bowlings, int userNumber) {
        IntStream.range(0, BowlingRound.LAST_ROUND_NUM + BONUS_ROUND)
                .forEach((idx) -> playByUserNumber(bowlings, userNumber));
    }

    private void playByUserNumber(Bowlings bowlings, int userNumber) {
        IntStream.of(0, userNumber)
                .forEach((num) -> bowlings.play(10));
    }

    @Test
    @DisplayName("현재 차례의 사용자를 반환해야 합니다.")
    void shouldReturnCurrentPlayingUser() {
        Bowlings bowlings = new Bowlings(List.of(new Username("abc"), new Username("kcs")));

        Username firstUser = bowlings.currentUser();
        bowlings.play(10);
        Username secondUser = bowlings.currentUser();

        assertThat(firstUser).isEqualTo(new Username("abc"));
        assertThat(secondUser).isEqualTo(new Username("kcs"));
    }

    @Test
    @DisplayName("사용자별로 게임을 시도합니다.")
    void shouldPlayGameByUser() {
        Bowlings bowlings = new Bowlings(List.of(new Username("abc"), new Username("kcs")));

        ScoreResult firstResult = bowlings.play(10);
        ScoreResult secondResult = bowlings.play(10);

        assertThat(firstResult.isSameUsername(new Username("abc")));
        assertThat(secondResult.isSameUsername(new Username("kcs")));
    }
}
