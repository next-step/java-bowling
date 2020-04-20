package bowling.frame;

import bowling.BowlingGame;
import bowling.Pin;
import bowling.Player;
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
        bowlingGame.bowl(Player.of("AAA"), Pin.of(5));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(4));//1
        bowlingGame.bowl(Player.of("AAA"), Pin.of(7));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(3));//2
        bowlingGame.bowl(Player.of("AAA"), Pin.of(8));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(1));//3
        bowlingGame.bowl(Player.of("AAA"), Pin.of(10));//4
        bowlingGame.bowl(Player.of("AAA"), Pin.of(10));//5
        bowlingGame.bowl(Player.of("AAA"), Pin.of(10));//6
        bowlingGame.bowl(Player.of("AAA"), Pin.of(8));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(1));//7
        bowlingGame.bowl(Player.of("AAA"), Pin.of(10));//8
        bowlingGame.bowl(Player.of("AAA"), Pin.of(9));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(1));//9
        bowlingGame.bowl(Player.of("AAA"), Pin.of(0));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(10));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(4));//10
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
