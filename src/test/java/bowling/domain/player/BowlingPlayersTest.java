package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 게임 참가자를 표현하기 위한 클래스 테스트")
class BowlingPlayersTest {

    @DisplayName("초기화를 위해서 이름 리스트가 필요하다")
    @Test
    void init() {
        List<String> names = Arrays.asList("NOK", "CHA", "KKH");
        assertThat(BowlingPlayers.init(names)).isInstanceOf(BowlingPlayers.class);
    }

}
