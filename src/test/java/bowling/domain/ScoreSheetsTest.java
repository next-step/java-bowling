package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreSheetsTest {

    @DisplayName("현재 프래임셋에 끝나지 않은 프래임이 존재하는 상태에서 다음 프래임셋을 진행하려고 하면 exception 을 던진다")
    @Test
    void failToNextFrameSet(){
        ScoreSheets scoreSheets = new ScoreSheets(Arrays.asList(new Player("NIO"), new Player("HOON")));

        FrameSet frameSet = scoreSheets.nextFrameSet();
        // do not play and.. nextFrameSet
        assertThatThrownBy(() -> scoreSheets.nextFrameSet())
                .isInstanceOf(IllegalStateException.class);
    }

}