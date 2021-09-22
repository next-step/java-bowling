package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class PlayerTest {

    @Test
    void createPerson() {
        // Given
        String givenName = "PHS";
        Player player = Player.from(givenName);

        assertThat(player.name()).isEqualTo(givenName);
    }


    @DisplayName("영어가 아닌 문자가 이름으로 들어가면 IllegalArgument 발생")
    @Test
    void createPersonWithIllegalArgumentException() {
        // Given
        String givenName = "박박박";

        assertThatIllegalArgumentException().isThrownBy(() -> Player.from(givenName));
    }

    @DisplayName("3자리 이상의 문자가 들어가면 IllegalArgumentException")
    @Test
    void createPersonMoreThan3lettersWithIllegalArgumentException() {
        // Given
        String givenName = "ABCD";

        assertThatIllegalArgumentException().isThrownBy(() -> Player.from(givenName));
    }

}