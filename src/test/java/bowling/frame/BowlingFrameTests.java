package bowling.frame;

import bowling.BowlingGame;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 프레임 공통 테스트")
public class BowlingFrameTests {

    private static final List<BowlingFrame> bowlingFrames;

    static {
        BowlingGame bowlingGame = BowlingGame.newInstance("AAA");
        bowlingGame.bowl(Pin.of(5));
        bowlingGame.bowl(Pin.of(4));
        bowlingGame.bowl(Pin.of(7));
        bowlingGame.bowl(Pin.of(3));
        bowlingGame.bowl(Pin.of(8));
        bowlingGame.bowl(Pin.of(1));
        bowlingGame.bowl(Pin.of(10));
        bowlingGame.bowl(Pin.of(10));
        bowlingGame.bowl(Pin.of(10));
        bowlingGame.bowl(Pin.of(8));
        bowlingGame.bowl(Pin.of(1));
        bowlingGame.bowl(Pin.of(10));
        bowlingGame.bowl(Pin.of(9));
        bowlingGame.bowl(Pin.of(1));
        bowlingGame.bowl(Pin.of(0));
        bowlingGame.bowl(Pin.of(10));
        bowlingGame.bowl(Pin.of(4));
        BowlingFrames frames = bowlingGame.getBowlingFrames();
        bowlingFrames = frames.getFrames();
    }

    @DisplayName("프레임 스코어 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"0,9", "1,18", "2,9", "3,30", "4,28", "5,19", "6,9", "7,20", "8,10", "9,18"})
    public void getFrameScoreTest(final int index, final int expectedScore) {
        BowlingFrame bowlingFrame = bowlingFrames.get(index);
        assertThat(bowlingFrame.getFrameScore()).isEqualTo(Score.of(expectedScore));
    }
}
