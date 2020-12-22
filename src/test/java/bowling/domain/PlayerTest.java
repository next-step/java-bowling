package bowling.domain;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {
            "TAS:TAS",
            "KBY:KBY",
            "JYP:JYP"
    },delimiter = ':')
    @DisplayName("플레이어 생성 후 객체 확인")
    void player_normalName_isEqualTo(String name1, String name2) {
        assertThat(Player.from(name1)).isEqualTo(Player.from(name2));
    }

    @Test
    @DisplayName("플레이어 이름이 공백인 경우 IllegalArgumentException 발생")
    public void player_blankName_throwException() {
        assertThatThrownBy(() -> Player.from(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.MIN_NAME_LENGTH_ERROR);
    }

    @Test
    @DisplayName("플레이어 이름이 3글자를 넘은경우 IllegalArgumentException 발생")
    public void player_overSizeName_throwException() {
        assertThatThrownBy(() -> Player.from("ABCD"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.MAX_NAME_LENGTH_ERROR);

    }

    @Test
    @DisplayName("플레이어 이름이 공백인 경우 IllegalArgumentException 발생")
    public void player_noneEnglishName_throwException() {
        assertThatThrownBy(() -> Player.from("김병윤"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.NAME_REGEX_PATTERN_ERROR);
    }
}
