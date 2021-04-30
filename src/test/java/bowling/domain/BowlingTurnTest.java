package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class BowlingTurnTest {
    @Test
    void 생성_테스트() {
        // given
        BowlingTurn bowlingTurn = BowlingTurn.of("LDH");
        // when & then
        assertThat(bowlingTurn.frames().size()).isEqualTo(1);
        assertThat(bowlingTurn.player()).isEqualTo(Player.of("LDH").toString());
    }

    @Test
    void 플레이_테스트() {
        // given
        BowlingTurn bowlingTurn = BowlingTurn.of("LDH");
        // when & then
        assertThat(bowlingTurn.isDone()).isFalse();
        IntStream.range(0, 10).forEach(i -> {
            bowlingTurn.play(3);
            bowlingTurn.play(3);
            bowlingTurn.next();
        });
        assertThat(bowlingTurn.isDone()).isTrue();
    }
}
