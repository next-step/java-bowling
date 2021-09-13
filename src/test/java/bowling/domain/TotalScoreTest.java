package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalScoreTest {
    @DisplayName("TotalScore 생성 성공 테스트")
    @Test
    public void createTest() {
        assertThat(TotalScore.from(Arrays.asList(1, 2))).isInstanceOf(TotalScore.class);
    }

    @DisplayName("3번 투구시 - 1번 프레임 스트라이크, 2번 프레임 스페어 했을때 총점 테스트")
    @Test
    public void totalScoreStrikeAndSpare() {
        List<Integer> calculatedScores = new ArrayList<>();
        Frames frames = new Frames();

        frames.throwBalls(10);
        frames.throwBalls(8);
        frames.throwBalls(2);
        TotalScore.from(frames, calculatedScores);

        assertThat(calculatedScores.size()).isEqualTo(1);
        assertThat(calculatedScores).containsExactly(20);
    }

    @DisplayName("3번 스트라이크 했을때 총점 테스트")
    @Test
    public void totalScoreThreeStrike() {
        List<Integer> calculatedScores = new ArrayList<>();
        Frames frames = new Frames();

        frames.throwBalls(10);
        frames.throwBalls(10);
        frames.throwBalls(10);
        TotalScore.from(frames, calculatedScores);

        assertThat(calculatedScores.size()).isEqualTo(1);
        assertThat(calculatedScores).containsExactly(30);
    }

    @DisplayName("스페어 후 거터 테스트")
    @Test
    public void totalScoreSpareAndGutter() {
        List<Integer> calculatedScores = new ArrayList<>();
        Frames frames = new Frames();

        frames.throwBalls(1);
        frames.throwBalls(9);
        frames.throwBalls(0);
        TotalScore.from(frames, calculatedScores);

        assertThat(calculatedScores.size()).isEqualTo(1);
        assertThat(calculatedScores).containsExactly(10);
    }
}
