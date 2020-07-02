package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BowlingGameTest {

    @Test
    @DisplayName("생성 테스트")
    void start() {
        BowlingGame bowlingGame = BowlingGame.start(Player.of("PEJ"));

        assertThat(bowlingGame.whoseTurn()).isEqualTo("PEJ");
        assertThat(bowlingGame.getCurrentIndex()).isEqualTo(0);
        assertThat(bowlingGame.getFrames().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 실행 - 최종적으로 10개 프레임을 갖는다")
    void run() {
        BowlingGame bowlingGame = BowlingGame.start(Player.of("PEJ"));

        while (!bowlingGame.isLastFrame()) {
            bowlingGame.run(0);
            bowlingGame.next();
        }

        assertThat(bowlingGame.getFrames().size()).isEqualTo(10);

    }

}
