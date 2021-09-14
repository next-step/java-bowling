package bowling.domain.player;

import bowling.domain.frame.AllFrames;
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
class PlayersTest {
    List<String> playerNames;
    AllFrames allFrames;

    @BeforeAll
    void setUp() {
        playerNames = Arrays.asList("KCS", "KJY");
        allFrames = AllFrames.from(new ArrayList<>(), playerNames.size());
    }


    @DisplayName("init메서드에 이름 문자열 데이터를 입력 받으면, Players객체를 생성한다")
    @Test
    void createTest() {
         assertThat(Players.init(playerNames, allFrames)).isInstanceOf(Players.class);
    }

    @DisplayName("init메서드에 null데이터를 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> Players.init(null, allFrames)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("numberOfPlayers메서드를 호출하면 플레이어의 수를 반환한다")
    @Test
    void numberOfPlayersTest() {
         assertThat(Players.init(playerNames, allFrames).numberOfPlayers()).isEqualTo(playerNames.size());
    }
}
