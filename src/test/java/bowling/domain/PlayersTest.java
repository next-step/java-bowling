package bowling.domain;

import bowling.exception.BadSizeOfPlayersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayersTest {

    @ParameterizedTest
    @DisplayName("플레이어 수가 1보다 작으면 BadSizeOfPlayersException 이 발생한다.")
    @ValueSource(ints = {-9, -3, -1, 0})
    void of(int sizeOfPlayers) {
        assertThatExceptionOfType(BadSizeOfPlayersException.class)
                .isThrownBy(() -> Players.of(sizeOfPlayers, () -> new Player("GHO")))
                .withMessage("플레이어는 한명 이상이어야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("만들어진 플레이어의 숫자대로 forEach 함수가 실행되는지 확인")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void forEach(int sizeOfPlayers) {
        Players players = Players.of(sizeOfPlayers, () -> new Player("GHO"));
        List<Player> playerList = new LinkedList<>();
        players.forEach(playerList::add);
        assertThat(playerList.size())
                .isEqualTo(sizeOfPlayers);
    }
}
