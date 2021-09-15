package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardTest {
    List<String> playerNames = Arrays.asList("JHJ", "PJH");

    @DisplayName("ScoreBoard 객체 생성 성공 테스트")
    @Test
    public void createTest() {
        TotalFrames totalFrames = new TotalFrames(playerNames.size());
        Players players = new Players(playerNames, totalFrames);
        assertThat(ScoreBoard.from(players, totalFrames)).isInstanceOf(ScoreBoard.class);
    }
}
