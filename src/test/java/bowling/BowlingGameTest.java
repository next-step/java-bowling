package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class BowlingGameTest {

    @Test
    @DisplayName("참가자 명 반환 검증")
    void getPlayerName() {
        String expected = "abc";
        BowlingGame bowlingGame = BowlingGame.of(2, expected, 0);

        then(bowlingGame.getPlayerName()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"2", "3", "4"})
    @DisplayName("경기 종료 여부 검증")
    void isFinished(int totalFrames) {
        BowlingGame bowlingGame = BowlingGame.of(totalFrames, "abc", 0);

        IntStream.range(0, totalFrames + 1).forEach(index -> {
            then(bowlingGame.isFinished()).isFalse();
            bowlingGame.bowl(Pin.ofMax());
            if (bowlingGame.hasNextFrameAndIsCurrentFrameFinished()) {
                bowlingGame.shiftCurrentFrame();
            }
        });

        then(bowlingGame.isFinished()).isTrue();
    }

    @Test
    void hasNextFrameAndIsCurrentFrameFinished() {
        BowlingGame bowlingGame = BowlingGame.of(2, "abc", 0);

        then(bowlingGame.hasNextFrameAndIsCurrentFrameFinished()).isFalse();

        bowlingGame.bowl(Pin.ofMax());
        then(bowlingGame.hasNextFrameAndIsCurrentFrameFinished()).isTrue();
    }

    @Test
    void shiftCurrentFrame() {
        BowlingGame bowlingGame = BowlingGame.of(2, "abc", 0);

        bowlingGame.bowl(Pin.ofMax());
        bowlingGame.shiftCurrentFrame();

        assertThatThrownBy(bowlingGame::shiftCurrentFrame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("다음 프레임이 없습니다.");
    }
}
