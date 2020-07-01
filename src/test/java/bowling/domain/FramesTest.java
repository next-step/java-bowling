package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("Spare 처리 전에는 Null Score")
    @Test
    public void calculateTotalScore_BeforeSpare_ReturnNullScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(5));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.ofNull());
    }

    @DisplayName("Miss는 해당 투구만 점수로 계산")
    @Test
    public void calculateTotalScore_WithMiss_ReturnScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(3));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.of(8));
    }

    @DisplayName("Strike는 이후 0번 투구하면 Null Score")
    @Test
    public void calculateTotalScore_WithStrike_ReturnNullScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.ofNull());
    }

    @DisplayName("Strike는 이후 1번 투구하면 Null Score")
    @Test
    public void calculateTotalScore_WithStrikeAndOneBowling_ReturnNullScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.ofNull());
    }

    @DisplayName("Strike는 이후 2번 투구하면 Score")
    @Test
    public void calculateTotalScore_WithStrikeAndTwoBowling_ReturnScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.of(20));
    }

    @DisplayName("Spare 이후 0번 투구하면 Null Score")
    @Test
    public void calculateTotalScore_WithSpare_ReturnNullScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        List<Score> scores = frames.stream().map(Frame::calculateScore).collect(Collectors.toList());
        assertThat(scores.get(0)).isEqualTo(Score.ofNull());
    }

    @DisplayName("Spare는 이후 1번 투구하면 Score")
    @Test
    public void calculateTotalScore_WithSpareAndOneBowling_ReturnScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        List<Score> scores = frames.stream().map(Frame::calculateScore).collect(Collectors.toList());
        assertThat(scores.get(0)).isEqualTo(Score.of(15));
    }

    @DisplayName("Strike 2번 후 2번 투구하면 Score")
    @Test
    public void calculateTotalScore_WithTwoStrikeAndTwoBowling_ReturnScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.of(30));
        assertThat(scores.get(1)).isEqualTo(Score.of(60));
        assertThat(scores.get(2)).isEqualTo(Score.ofNull());
        assertThat(scores.get(3)).isEqualTo(Score.ofNull());
    }

    @DisplayName("Strike 3번 후 Miss 1번")
    @Test
    public void Strike3_Miss1() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(5));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.of(30));
        assertThat(scores.get(1)).isEqualTo(Score.of(55));
        assertThat(scores.get(2)).isEqualTo(Score.ofNull());
        assertThat(scores.get(3)).isEqualTo(Score.ofNull());
    }

    @DisplayName("10 프레임이 종료되지 않으면 Null Score")
    @Test
    public void calculateTotalScore_NotFinishGame_ReturnNullScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(7));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.of(30));
        assertThat(scores.get(1)).isEqualTo(Score.of(55));
        assertThat(scores.get(2)).isEqualTo(Score.of(75));
        assertThat(scores.get(3)).isEqualTo(Score.of(90));
        assertThat(scores.get(4)).isEqualTo(Score.of(105));
        assertThat(scores.get(5)).isEqualTo(Score.of(113));
        assertThat(scores.get(6)).isEqualTo(Score.of(119));
        assertThat(scores.get(7)).isEqualTo(Score.of(125));
        assertThat(scores.get(8)).isEqualTo(Score.of(140));
        assertThat(scores.get(9)).isEqualTo(Score.ofNull());
    }

    @DisplayName("10 프레임이 종료되면 Score")
    @Test
    public void calculateTotalScore_FinishGame_ReturnScore() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(10));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(3));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        frames.bowling(new Pin(5));
        List<Score> scores = frames.calculateTotalScore();
        assertThat(scores.get(0)).isEqualTo(Score.of(30));
        assertThat(scores.get(1)).isEqualTo(Score.of(60));
        assertThat(scores.get(2)).isEqualTo(Score.of(90));
        assertThat(scores.get(3)).isEqualTo(Score.of(120));
        assertThat(scores.get(4)).isEqualTo(Score.of(150));
        assertThat(scores.get(5)).isEqualTo(Score.of(180));
        assertThat(scores.get(6)).isEqualTo(Score.of(203));
        assertThat(scores.get(7)).isEqualTo(Score.of(219));
        assertThat(scores.get(8)).isEqualTo(Score.of(225));
        assertThat(scores.get(9)).isEqualTo(Score.of(240));
    }
}
