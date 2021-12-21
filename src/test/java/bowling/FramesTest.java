package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

    private final ScoresFactory scoresFactory = new BowlingScoresFactory();

    @Test
    void createTest() {
        List<Frame> emptyFrames = new ArrayList<>();
        assertThatCode(
            () -> new Frames(emptyFrames, new BowlingScoresFactory())).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("마지막 프레임을 반환한다.")
    void getLastFrameTest() {
        List<Frame> mockFrames = IntStream.range(0, 2)
            .boxed()
            .map(round -> new Frame(new GeneralScores(1, 2)))
            .collect(Collectors.toList());

        Frames frames = new Frames(mockFrames, scoresFactory);

        assertThat(frames.getLastFrame()).isEqualTo(new Frame(new GeneralScores(1, 2)));
    }

    @Test
    @DisplayName("경기 최초에 프레임을 반환할 경우, 비어있는 프레임이 반환된다.")
    void getLastFrameLastTest() {

        assertThat(new Frames().getLastFrame()).isEqualTo(new Frame(scoresFactory.create(0)));
    }

}
