package bowling.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MultiBowlingGameTest {

    @DisplayName("List<String> 플레이어 이름 명단을 받으면 그 크기만큼의 List<SingleBowlingGame> 컬렉션을 생성함")
    @Test
    public void makeMultiBowlingGame() {
        List<String> playerNames = Arrays.asList("PJH", "JKA", "TTD");

        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);

        assertThat(multiBowlingGame.getPlayerCounts()).isEqualTo(3);
    }


}
