package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import bowling.domain.frame.Frames;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccumulatedScoresTest {
    AccumulatedScores accumulatedScores;
    Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.from(Arrays.asList(1, 2));
    }

    @DisplayName("Score객체 데이터를 가지고 있는 Frames 객체를 입력 받으면, AccumulatedScores 객체를 생성한다")
    @Test
    void createTest() {
        assertThat(AccumulatedScores.from(frames)).isInstanceOf(AccumulatedScores.class);
    }

    @DisplayName("Score객체 데이터를 가지고 있는 Frames 객체를 입력 받으면, 누적된 점수 리스트 객체를 생성한다")
    @Test
    void getCumulativeScoresTest() {
        accumulatedScores = AccumulatedScores.from(frames);
        assertThat(accumulatedScores.getCumulativeScores(frames)).containsExactly(3);
    }

    @DisplayName("addScore메서드에 Score객체 입력받으면 누적된 점수 리스트 객체를 추가로 생성한다")
    @Test
    void addScoreTest() {
        accumulatedScores = AccumulatedScores.from(frames);
        accumulatedScores.addScore(Score.from(4));
        accumulatedScores.addScore(Score.from(5));
        assertThat(accumulatedScores.getCumulativeScores(frames)).containsExactly(3, 12);
    }
}
