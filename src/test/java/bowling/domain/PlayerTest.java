package bowling.domain;

import bowling.exception.InvalidPlayersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerTest {

    @DisplayName("Player 생성 테스트")
    @Test
    void create() {
        Player player = Player.from("syd");

        assertThat(player).isEqualTo(Player.from("syd"));
    }

    @DisplayName("Player 길이 테스트")
    @Test
    void length() {
        Player player = Player.from("syd");

        assertThat(player.length()).isEqualTo(3);
    }

    @DisplayName("참가자 중 3글자 이상 Exception 확인")
    @Test
    void InvalidPlayersNameExceptionTest() {
        assertThatExceptionOfType(InvalidPlayersException.class)
                .isThrownBy(() -> {
                    Player.from("SYDD");
                }).withMessageMatching("참가자 이름은 반드시 3자 이내 이어야 합니다 : SYDD");
    }
}
