package bowling_refactor;

import bowling_refactor.domain.Frame;
import bowling_refactor.domain.FrameNumber;
import bowling_refactor.domain.Frames;
import bowling_refactor.domain.Score;
import bowling_refactor.domain.state.complete_state.Miss;
import bowling_refactor.domain.state.complete_state.Spare;
import bowling_refactor.domain.state.complete_state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1:2",
            "4:5",
            "9:10"
    }, delimiter = ':')
    @DisplayName("원하는 인덱스 찾기")
    void findIndex(int index, int frameNumber) {
        Frames frames = new Frames();
        Frame frame = frames.getFrame(index);

        assertThat(frame.getFrameNumber()).isEqualTo(FrameNumber.of(frameNumber));
    }

    @Test
    @DisplayName("pitch test")
    void hit() {
        List<Frame> frameList = Arrays.asList(new Frame(1), new Frame(2));
        Frames frames = new Frames(frameList);
        int index = 0;
        int pin = 5;
        frames.hit(index, pin);
        assertThat(frameList.get(index).getScore(0)).isEqualTo(Score.ofNoneScore());
    }

    @Test
    @DisplayName("남은 핀 있는지 테스트")
    void isLeftPin() {
        Frame frame = new Frame(2, new Strike());
        List<Frame> frameList = Arrays.asList(new Frame(1), frame);
        Frames frames = new Frames(frameList);

        assertThat(frames.isLeftPin(0)).isTrue();
        assertThat(frames.isLeftPin(1)).isFalse();
    }

    @Test
    @DisplayName("score 구하기")
    void getScore() {
        List<Frame> frameList = Arrays.asList(
                new Frame(1, new Strike()),
                new Frame(2, new Spare(5, 5)),
                new Frame(3, new Miss(5,1)),
                new Frame(4, new Miss(5, 4))
        );

        Frames frames = new Frames(frameList);
        assertThat(frames.getScore(0).getScore()).isEqualTo(20);
        assertThat(frames.getScore(1).getScore()).isEqualTo(35);
        assertThat(frames.getScore(2).getScore()).isEqualTo(41);
        assertThat(frames.getScore(3).getScore()).isEqualTo(50);
    }


}
