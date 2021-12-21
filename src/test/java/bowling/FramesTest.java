package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.factory.BowlingScoresFactory;
import bowling.domain.factory.ScoresFactory;
import bowling.domain.scores.GeneralScores;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FramesTest {

    private static final int STRIKE = 10;
    private static final int[] END_OF_NINE_ROUND =
        new int[]{STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE};

    private final ScoresFactory scoresFactory = new BowlingScoresFactory();

    @Test
    void createTest() {
        List<Frame> emptyFrames = new ArrayList<>();
        assertThatCode(
            () -> new Frames(new BowlingScoresFactory(), emptyFrames)).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("마지막 프레임을 반환한다.")
    void getLastFrameTest() {
        List<Frame> mockFrames = IntStream.range(0, 2)
            .boxed()
            .map(round -> new Frame(new GeneralScores(1, 2)))
            .collect(Collectors.toList());

        Frames frames = new Frames(scoresFactory, mockFrames);

        assertThat(frames.getLastFrame()).isEqualTo(new Frame(new GeneralScores(1, 2)));
    }

    @Test
    @DisplayName("경기 최초에 프레임을 반환할 경우, 비어있는 프레임이 반환된다.")
    void getLastFrameLastTest() {
        assertThat(new Frames().getLastFrame()).isEqualTo(new Frame(scoresFactory.create(0)));
    }

    @Test
    @DisplayName("Strike 수행시, 다음 프레임에 기록되어야 한다.")
    void addGeneralFrameStrikeTest() {
        Frames frames = new Frames()
            .add(STRIKE)
            .add(STRIKE)
            .add(STRIKE);

        assertThat(frames.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Strike가 아닌경우, 다음 투구는 동일 프레임에 기록되어야 한다.")
    void addGeneralFrameTest() {
        Frames frames = new Frames().add(3).add(7);
        assertThat(frames.size()).isEqualTo(1);

        Frames addFrame = frames.add(3).add(3).add(3);
        assertThat(addFrame.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("마지막 프레임 수행시, 다음 프레임에 기록되어야 하며, STRIKE 또는 SPARE이 있는경우, 한번더 투구할 수 있다.")
    void addFinalFrameTest() {
        Frames finalStartFrames = new Frames(scoresFactory, END_OF_NINE_ROUND);
        assertThat(finalStartFrames.size()).isEqualTo(9);

        Frames addFrame = finalStartFrames.add(3).add(7);// SPARE
        assertThat(addFrame.size()).isEqualTo(10);

        Frames finalAddFrame = addFrame.add(STRIKE);
        assertThat(finalAddFrame.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("마지막 프레임 수행시, 다음 프레임에 기록되어야 하며, STRIKE 또는 SPARE이 없는 경우, 투구는 최대 2회이다.")
    void addFinalFrameWithExceptionTest() {
        Frames finalStartFrames = new Frames(scoresFactory, END_OF_NINE_ROUND);
        assertThat(finalStartFrames.size()).isEqualTo(9);

        Frames addFrame = finalStartFrames.add(3).add(3);// NON_SPARE
        assertThat(addFrame.size()).isEqualTo(10);

        assertThatIllegalArgumentException().isThrownBy(() -> addFrame.add(3));
    }

}
