package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @DisplayName("스트라이크를 친 후 공을 생성")
    @Test
    void strikeBowl() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(10);
        Frame next = frame.create();
        assertThat(next.getFrameNum()).isEqualTo(2);
    }

    @DisplayName("스페어를 친 후 공을 생성")
    @Test
    void spareBowl() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1);
        normalFrame.bowl(9);
        Frame next = normalFrame.create();
        assertThat(next.getFrameNum()).isEqualTo(2);
    }

    @DisplayName("미스를 친 후 공을 생성")
    @Test
    void missBowl() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1);
        normalFrame.bowl(0);
        Frame next = normalFrame.create();
        assertThat(next.getFrameNum()).isEqualTo(2);
    }

    @DisplayName("첫번째 프레임일때 보드를 생성해본다.")
    @Test
    void createBoard() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame
                .bowl(8).bowl(2);
        NormalFrame next = (NormalFrame) normalFrame.create();
        Board board = normalFrame.createBoard();
        List<FrameResult> frameResults = board.getFrameResults();
        assertThat(frameResults.get(0).getDisplayScore()).isEqualTo("8|/");
        assertThat(frameResults.get(0).getTotalScore()).isEqualTo(-1);
    }
}
