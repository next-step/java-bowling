package bowling.domain.score;

import bowling.domain.frame.AllFrames;
import bowling.domain.player.Players;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TotalScoreBoardTest {
    List<String> playerNames;

    @BeforeAll
    void setUp() {
        playerNames = Arrays.asList("KCS", "KJY");
    }

    @DisplayName("정적팩터리 메서드를 이용하여 초기화 하면, TotalScoreBoard객체를 생성한다")
    @Test
    void createTest() {
        Players players = Players.init(playerNames, AllFrames.from(new ArrayList<>(), playerNames.size()));
        assertThat(TotalScoreBoard.from(
            players,
            AllFrames.from(new ArrayList<>(), playerNames.size()))
        ).isInstanceOf(TotalScoreBoard.class);
    }

    @DisplayName("정적팩터리 메서드에 null데이터를 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest() {
        assertThatThrownBy(() ->
            TotalScoreBoard.from(null, AllFrames.from(new ArrayList<>(), playerNames.size()))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
