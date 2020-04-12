package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @DisplayName("프레임의 결과가 Gutter 또는 Miss 일 때, 프레임의 점수를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0", "8:1", "5:4"}, delimiter = ':')
    void returnFrameScoreWhenGutterOrMiss(int firstPoint, int secondPoint) {
        //given
        Frame frame = new Frame(0, firstPoint, secondPoint);
        Frames frames = new Frames(Arrays.asList(frame));

        //when
        int frameScore = frames.getFrameScore(frame.getFrameId());

        //then
        assertThat(frameScore).isEqualTo(firstPoint + secondPoint);
    }

    @DisplayName("프레임의 결과가 Spare일 때, 프레임의 점수는 다음 투구의 첫 번째 포인트를 합산해 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"9:1", "8:2", "0:10"}, delimiter = ':')
    void returnFrameScoreWhenSpare(int firstPoint, int secondPoint) {
        //given
        Frame frame = new Frame(0, firstPoint, secondPoint);
        Frame nextFrame = frame.createNextFrame();
        Frames frames = new Frames(Arrays.asList(frame, nextFrame));

        //when
        int frameScore = frames.getFrameScore(frame.getFrameId());

        //then
        assertThat(frameScore)
                .isEqualTo(firstPoint + secondPoint + nextFrame.getFirstPoint());
    }
}