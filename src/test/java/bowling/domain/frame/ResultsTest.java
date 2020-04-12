package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultsTest {

    private LinkedList<Frame> bowlingStates;

    @BeforeEach
    void setUp() {
        LinkedList<Frame> frames = new LinkedList<>();
        frames.add(new NormalFrame(1));
        Bowling bowling = new Bowling(frames, new Player("KSJ"));
        bowling.bowl(new Pin(5));
        bowling.bowl(new Pin(2));

        bowling.bowl(new Pin(2));
        bowling.bowl(new Pin(3));
        bowlingStates = bowling.getFrames();
    }

    @Test
    @DisplayName("프레임 결과 값 확인")
    void getResultFrame() {
        Results resultsWithFrameInfo = new Results(bowlingStates);
        List<Result> results = resultsWithFrameInfo.getResults();
        assertThat(results.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("프레임 결과 값 총 스코어")
    void getTotalScoreByResults() {
        // give
        Results resultsWithFrameInfo = new Results(bowlingStates);
        int actualTotalScore = resultsWithFrameInfo.getTotalScore();
        int expectedTotalScore = 5+2+2+3;

        assertThat(actualTotalScore).isEqualTo(expectedTotalScore);
    }
}
