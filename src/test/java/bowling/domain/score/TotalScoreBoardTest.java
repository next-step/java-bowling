package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.player.Players;

public class TotalScoreBoardTest {
    @DisplayName("정적팩터리 메서드를 이용하여 초기화 하면, TotalScoreBoard객체를 생성한다")
    @Test
    void createTest() {
        Players players = Players.init(Arrays.asList("KCS", "KJY"));
        assertThat(TotalScoreBoard.from(players)).isInstanceOf(TotalScoreBoard.class);
    }

    @DisplayName("정적팩터리 메서드에 null데이터를 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> TotalScoreBoard.from(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
