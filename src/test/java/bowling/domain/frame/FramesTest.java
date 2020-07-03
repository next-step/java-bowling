package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.FrameScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


public class FramesTest {

    @DisplayName("10개의 프레임 목록을 가진다")
    @Test
    void create() {
        Frames frames = Frames.create();
        List<Frame> frameList = frames.getContent();

        assertThat(frameList.size()).isEqualTo(10);
    }

    @DisplayName("마지막 프레임은 FinalFrame 인스턴스다")
    @Test
    void finalFrame() {
        Frames frames = Frames.create();
        List<Frame> frameList = frames.getContent();

        assertThat(frameList.get(frameList.size() - 1)).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("새로운 점수를 기록한다")
    @Test
    void addScore() {
        // given
        Frames frames = new Frames(Collections.singletonList(NormalFrame.createFirst()));
        Score score1 = Score.of(1);
        Score score2 = Score.of(2);

        // when
        frames.addScore(score1);
        frames.addScore(score2);

        // then
        List<Frame> frameList = frames.getContent();
        Frame frame = frameList.get(0);
        FrameScore frameScore = frame.getFrameScore();

        assertAll(
                () -> assertThat(frameScore.getFirst()).isEqualTo(score1),
                () -> assertThat(frameScore.getSecond()).isEqualTo(score2));
    }

    @DisplayName("더 이상 점수를 기록할 수 없으면 IllegalStateException")
    @Test
    void addScore_full() {
        Frames frames = new Frames(Collections.singletonList(NormalFrame.createFirst()));
        Score score = Score.of(1);
        frames.addScore(score);
        frames.addScore(score);

        assertThatThrownBy(() -> frames.addScore(score))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("더 이상");
    }

    @DisplayName("점수를 더 기록할 수 있는지 확인한다")
    @ParameterizedTest
    @MethodSource("canAddMoreScoreArguments")
    void canAddMoreScore(boolean canAddMoreScore, boolean expected) {
        assertThat(canAddMoreScore).isEqualTo(expected);
    }

    public static Stream<Arguments> canAddMoreScoreArguments() {
        Frames frames = new Frames(Collections.singletonList(NormalFrame.createFirst()));
        boolean canAddMoreScore1 = frames.canAddMoreScore();

        frames.addScore(Score.of(1));
        frames.addScore(Score.of(1));
        boolean canAddMoreScore2 = frames.canAddMoreScore();

        return Stream.of(
                Arguments.of(canAddMoreScore1, true),
                Arguments.of(canAddMoreScore2, false));
    }
}
