package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BowlingGameTest {

    @Test
    @DisplayName("생성 테스트")
    void start() {
        BowlingGame bowlingGame = BowlingGame.start(Player.of("PEJ"));

        assertThat(bowlingGame.whoseTurn()).isEqualTo("PEJ");
        assertThat(bowlingGame.getCurrentIndex()).isEqualTo(1);
        assertThat(bowlingGame.getFrames().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 실행")
    void run() {
        BowlingGame bowlingGame = BowlingGame.start(Player.of("PEJ"));
        assertThatCode(() -> bowlingGame.run(10)).doesNotThrowAnyException();
    }

}
