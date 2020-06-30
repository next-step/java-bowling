package camp.nextstep.edu.nextstep8.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    @DisplayName("해당 프레임에 대한 결과 값이 잘 나오는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "1:X",
            "2:9|/",
            "3:4|5",
            "4:-"
    }, delimiter = ':')
    public void getFrameResultTest(int frameIndex, String expected) {
        // given
        Frame frame1 = new Frame(10); // Strike
        Frame frame2 = new Frame(9).updateSpare(1); // Spare
        Frame frame3 = new Frame(4).updateSpare(5); // Gutter
        Frame frame4 = new Frame(0).updateSpare(0); // Miss
        ScoreBoard scoreBoard = new ScoreBoard( new HashMap<Integer, Frame>() {
            {
                put(1, frame1);
                put(2, frame2);
                put(3, frame3);
                put(4, frame4);
            }
        });

        // when
        String result = scoreBoard.getFrameResult(frameIndex);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
