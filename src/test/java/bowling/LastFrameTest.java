package bowling;

import bowling.entity.Pin;
import bowling.entity.frame.LastFrame;
import bowling.entity.frame.NormalFrame;
import bowling.entity.score.Miss;
import bowling.entity.score.Spare;
import bowling.entity.score.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LastFrameTest {
    @Test
    @DisplayName("2번 진행 시 종료")
    public void twoBowlGameEnd(){
        LastFrame lastFrame = new LastFrame();

        lastFrame.pinResult(new Pin(5));
        lastFrame.pinResult(new Pin(4));

        assertThat(lastFrame.bowlingGameEnd()).isTrue();
    }

    @Test
    @DisplayName("3번 진행 시 종료")
    public void threeBowlGameEnd(){
        LastFrame lastFrame = new LastFrame();

        lastFrame.pinResult(new Pin(10));
        lastFrame.pinResult(new Pin(5));
        lastFrame.pinResult(new Pin(4));

        assertThat(lastFrame.bowlingGameEnd()).isTrue();
    }
}
