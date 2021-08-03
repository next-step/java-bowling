package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.Fixture.PLAYER_NAMES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("볼링 게임 참가자를 표현하기 위한 클래스 테스트")
class BowlingPlayersTest {

    @DisplayName("초기화를 위해서 이름 리스트가 필요하다")
    @Test
    void init() {
        assertThat(BowlingPlayers.init(PLAYER_NAMES)).isInstanceOf(BowlingPlayers.class);
    }

    @DisplayName("이름 리스트가 null 일 경우 예외를 발생 시킨다")
    @Test
    void initException() {
        assertThatThrownBy(() -> BowlingPlayers.init(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 플레이어 테스트")
    @Test
    void firstPlayer() {
        BowlingPlayers bowlingPlayers = new BowlingPlayers(PLAYER_NAMES);

        assertThat(bowlingPlayers.firstPlayer().getName()).isEqualTo(PLAYER_NAMES.get(0));
    }

}
