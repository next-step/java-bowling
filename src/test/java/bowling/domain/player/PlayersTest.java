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


    @DisplayName("Players 인스턴스 생성에 Null 입력시 예외처리 여부 테스트")
    @Test
    void 검증_null() {
        // given
        List<Player> nullList = null;

        // when and then
        assertThatThrownBy(() -> new Players(nullList))
                .isInstanceOf(PlayerListNullPointerException.class)
                .hasMessage("List<Player> 인스턴스가 null 입니다.");

    }


}