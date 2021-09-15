package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalScoresTest {
    @DisplayName("TotalScores 생성 성공 테스트")
    @Test
    public void createTest() {
        assertThat(TotalScores.from(Arrays.asList(1, 2))).isInstanceOf(TotalScores.class);
    }

    @DisplayName("3번 투구시 - 1번 프레임 스트라이크, 2번 프레임 스페어 했을때 총점 테스트")
    @Test
    public void totalScoreStrikeAndSpare() {
        List<Integer> calculatedScores = new ArrayList<>();
        Frames frames = new Frames();

        frames.throwBalls(10);
        frames.throwBalls(8);
        frames.throwBalls(2);
        TotalScores.from(frames, calculatedScores);

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
        TotalScores.from(frames, calculatedScores);

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
        TotalScores.from(frames, calculatedScores);

        assertThat(calculatedScores.size()).isEqualTo(1);
        assertThat(calculatedScores).containsExactly(10);
    }

    @DisplayName("스트라이크 후 다음 2번의 투구까지 점수를 합산 했을때 총점 테스트")
    @Test
    public void totalStrikeTotalScore() {
        List<Integer> calculatedScores = new ArrayList<>();
        Frames frames = new Frames();

        frames.throwBalls(10);
        frames.throwBalls(1);
        frames.throwBalls(2);
        TotalScores.from(frames, calculatedScores);

        assertThat(calculatedScores.size()).isEqualTo(1);
        assertThat(calculatedScores).containsExactly(13);
    }

    @DisplayName("스페어 후 다음 1번의 투구까지 점수를 합산 했을때 총점 테스트")
    @Test
    public void totalSpareTotalScore() {
        List<Integer> calculatedScores = new ArrayList<>();
        Frames frames = new Frames();

        frames.throwBalls(3);
        frames.throwBalls(7);
        frames.throwBalls(6);
        TotalScores.from(frames, calculatedScores);

        assertThat(calculatedScores.size()).isEqualTo(1);
        assertThat(calculatedScores).containsExactly(16);
    }
}
