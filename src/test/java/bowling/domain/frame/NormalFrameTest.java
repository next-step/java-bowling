package bowling.domain.frame;

import bowling.domain.framestatus.Gutter;
import bowling.domain.framestatus.Miss;
import bowling.domain.framestatus.Spare;
import bowling.domain.framestatus.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    @DisplayName("NormalFrame 객체 비교")
    void compareToFrame() {
        // give
        NormalFrame actualNormalFrame = new NormalFrame(1);
        NormalFrame expectedNormalFrame = new NormalFrame(1);

        // when
        boolean same = actualNormalFrame.equals(expectedNormalFrame);

        // then
        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("프레임 상태 확인")
    void bowlTest() {
        // give
        Frame strikeNormalFrame = new NormalFrame(1);
        strikeNormalFrame.bowl(10);

        Frame spareNormalFrame = new NormalFrame(2);
        spareNormalFrame.bowl(2);
        spareNormalFrame.bowl(8);

        Frame missNormalFrame = new NormalFrame(3);
        missNormalFrame.bowl(2);
        missNormalFrame.bowl(3);

        Frame gutterNormalFrame = new NormalFrame(4);
        gutterNormalFrame.bowl(0);
        gutterNormalFrame.bowl(0);


        assertThat(strikeNormalFrame.getFrameStatus()).isInstanceOf(Strike.class);
        assertThat(spareNormalFrame.getFrameStatus()).isInstanceOf(Spare.class);
        assertThat(missNormalFrame.getFrameStatus()).isInstanceOf(Miss.class);
        assertThat(gutterNormalFrame.getFrameStatus()).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("프레임 점수 가져오기")
    void getScore() {
        Frame frame = new NormalFrame(2);
        frame.bowl(2);
        frame.bowl(8);

        assertThat(frame.getScore()).isEqualTo(10);

    }
}
