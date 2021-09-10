package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerNameTest {

    @ParameterizedTest(name = "플레이어 이름은 영문 3글자이다. - 성공 테스트")
    @ValueSource(strings = {"cyb", "CYB"})
    public void createPlayerNameSuccessTest(String nameInput) {
        PlayerName playerName = new PlayerName(nameInput);
        assertThat(playerName.toString()).contains(nameInput);
    }

    @ParameterizedTest(name = "플레이어 이름은 영문 3글자이다. - 실패 테스트")
    @ValueSource(strings = {"A", "AB", "ABCD", "가나다"})
    @NullAndEmptySource
    public void createPlayerNameFailTest(String nameInput) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName(nameInput))
                .withMessageContaining(String.valueOf(nameInput));
    }

}