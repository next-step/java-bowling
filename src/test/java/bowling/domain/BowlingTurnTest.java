package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import bowling.domain.frame.Frames;

public class BowlingTurnTest {
    @Test
    void 생성_테스트() {
        // given
        BowlingTurn bowlingTurn = BowlingTurn.of("LDH");
        // when & then
        assertThat(bowlingTurn.frames()).isEqualTo(Frames.init().getFrames());
        assertThat(bowlingTurn.player()).isEqualTo(Player.of("LDH").toString());
    }

    @Test
    void 플레이_테스트() {
        // given
        BowlingTurn bowlingTurn = BowlingTurn.of("LDH");
        // when & then
        assertThat(bowlingTurn.isEndAllTurn()).isFalse();
        IntStream.range(0, 10).forEach(i -> {
            bowlingTurn.play(3);
            bowlingTurn.play(3);
            assertThat(bowlingTurn.isEndTurn()).isTrue();
        });
        assertThat(bowlingTurn.isEndAllTurn()).isTrue();

    }
}
