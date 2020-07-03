package bowling.step4;

import bowling.step4.domain.process.BowlingGame;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {

    @Test
    @DisplayName("게임 진행 메소드 테스트")
    void playTest() {
        List<Integer> pitches = Lists.list(10,10,10,10,10,10,10,10,10);
        BowlingGame bowlingGame = BowlingGame.of("BJR");
        for (int pitch : pitches){
            bowlingGame = bowlingGame.play(pitch);
        }
        // 9프레임까지 play를 한 것이니, 현재 프레임은 10
        assertThat(bowlingGame.getFrameNo()).isEqualTo(10);
    }

    @Test
    @DisplayName("게임 종료 여부 메소드 테스트")
    void isGameOverTest() {
        List<Integer> pitches = Lists.list(10,10,10,10,10,10,10,10,10,9,1,5);
        BowlingGame bowlingGame = BowlingGame.of("BJR");
        for (int pitch : pitches){
            bowlingGame = bowlingGame.play(pitch);
        }
        assertThat(bowlingGame.isGameOver()).isTrue();
    }
}
