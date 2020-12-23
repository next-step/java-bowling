package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @DisplayName("Player 생성 테스트")
    @Test
    void playerConstructorTest(){

        // given
        String playerName = "AAA";

        // when
        Player player = Player.from(playerName);

        // then
        assertThat(player).isEqualTo(Player.from(playerName));
    }

    @DisplayName("Player 이름 유효성 위반 시 Exception test")
    @ParameterizedTest
    @ValueSource(strings = {"비비비", "JANE", "123", "$$$"})
    void illegalPlayerNameExceptionTest(String input){

        assertThatIllegalArgumentException().isThrownBy(() -> {

            Player.from(input);

        }).withMessageContaining("플레이어의 이름을 영문자 세글자로 입력하세요.");
    }

}
