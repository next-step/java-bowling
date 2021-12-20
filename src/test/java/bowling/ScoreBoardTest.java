package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {

    @Test
    void createTest() {
        List<Frame> emptyFrames = new ArrayList<>();
        assertThatCode(() -> new ScoreBoard(emptyFrames)).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("마지막 프레임을 반환한다.")
    void getLastFrameTest() {
        List<Frame> mockFrames = IntStream.range(0, 2)
            .boxed()
            .map(round -> new Frame(round, new Scores(1, 2)))
            .collect(Collectors.toList());

        ScoreBoard scoreBoard = new ScoreBoard(mockFrames);

        assertThat(scoreBoard.getLastFrame()).isEqualTo(new Frame(1, new Scores(1, 2)));
    }

    @Test
    @DisplayName("경기 최초에 프레임을 반환할 경우, 비어있는 프레임이 반환된다.")
    void getLastFrameLastTest() {
        ScoreBoard scoreBoard = new ScoreBoard();

        assertThat(scoreBoard.getLastFrame()).isEqualTo(new Frame(0));
    }

}
