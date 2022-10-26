package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BallingTest {

    @Test
    void shouldReturnCurrentRound() {
        Balling balling = new Balling();

        assertThat(balling.currentRound().isSameRound(new BallingRound(1))).isTrue();

        balling.play(5);
        balling.play(5);
        assertThat(balling.currentRound().isSameRound(new BallingRound(2))).isTrue();

        balling.play(10);
        assertThat(balling.currentRound().isSameRound(new BallingRound(3))).isTrue();
    }

    @Test
    void shouldReturnGameFinished() {
        Balling balling = new Balling();

        playUntilFinish(balling);
        assertThat(balling.isFinish()).isTrue();
    }

    private void playUntilFinish(Balling balling) {
        IntStream.range(0, BallingRound.LAST_ROUND_NUM)
                .forEach((idx) -> balling.play(10));
        balling.play(10);
        assertThat(balling.isFinish()).isFalse();
        balling.play(10);
    }

    @Test
    @DisplayName("쓰러트린 핀 개수 추가 및 현재 볼링 라운드 위치를 변경해야 합니다.")
    void testPlay() {
        Balling balling = new Balling();

        balling.play(10);
        BallingRound firstRound = balling.getRounds().get(new Position(1));

        assertThat(firstRound.getScores().getScores()).containsExactly(new Score(10));
        assertThat(firstRound.isSameRound(new BallingRound(1))).isTrue();

        balling.play(5);
        balling.play(3);
        BallingRound secondRound = balling.getRounds().get(new Position(2));

        assertThat(secondRound.getScores().getScores()).containsExactly(new Score(5), new Score(3));
        assertThat(secondRound.isSameRound(new BallingRound(2))).isTrue();

        assertThat(balling.currentRound().isSameRound(new BallingRound(3))).isTrue();
    }

}
