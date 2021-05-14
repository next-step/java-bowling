package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    @DisplayName("Players 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        List<Player> playerList = new ArrayList<>();

        // when
        Players players = new Players(playerList);

        // then
        assertThat(players).isNotNull();
    }


}