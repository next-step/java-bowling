package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalFramesTest {

    List<String> playerNames = Arrays.asList("JHJ", "PJH");

    @DisplayName("TotalFrames 객체 생성 성공 테스트")
    @Test
    public void createTest() {
        assertThat(new TotalFrames(playerNames.size())).isInstanceOf(TotalFrames.class);
    }

    @DisplayName("스트라이크 후 다음 사용자 턴으로 넘어가는지 테스트")
    @Test
    public void StrikeAfterIsNextTrueTest() {
        TotalFrames totalFrames = new TotalFrames(playerNames.size());
        Players players = new Players(playerNames, totalFrames);
        int playerIndex = 0;
        ScoreBoard scoreBoard = players.scoreBoard();
        int pitchingCount = 10;

        TotalScores totalScores = scoreBoard.totalScoreOf(playerIndex);
        totalScores.addScore(Score.valueOf(pitchingCount));
        totalFrames.throwBall(playerIndex, pitchingCount);

        assertThat(totalFrames.isNextPlayer(playerIndex)).isEqualTo(true);
    }

    @DisplayName("스트라이크 후 다음 사용자 턴으로 넘어가는지 테스트")
    @Test
    public void SpareAfterIsNextTrueTest() {
        TotalFrames totalFrames = new TotalFrames(playerNames.size());
        Players players = new Players(playerNames, totalFrames);
        int playerIndex = 0;
        ScoreBoard scoreBoard = players.scoreBoard();
        int pitchingCount = 1;

        TotalScores totalScores = scoreBoard.totalScoreOf(playerIndex);
        totalScores.addScore(Score.valueOf(pitchingCount));
        totalFrames.throwBall(playerIndex, pitchingCount);

        assertThat(totalFrames.isNextPlayer(playerIndex)).isEqualTo(false);

        pitchingCount = 9;
        totalScores.addScore(Score.valueOf(pitchingCount));
        totalFrames.throwBall(playerIndex, pitchingCount);

        assertThat(totalFrames.isNextPlayer(playerIndex)).isEqualTo(true);
    }
}
