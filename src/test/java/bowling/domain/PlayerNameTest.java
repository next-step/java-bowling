package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerNameTest {

    @Test
    void create() {
        String name = "tes";
        PlayerName playerName = new PlayerName(name);

        assertThat(playerName).isEqualTo(new PlayerName(name));
    }

    @Test
    @DisplayName("3글자 초과의 영어이름 생성시 예외 발생 테스트")
    void create_english_name_length_more_than_3_throw_exception() {
        String name = "tess";

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PlayerName(name));
    }

    @Test
    @DisplayName("숫자 포함한 이름으로 생성시 예외 발생 테스트")
    void create_name_with_number_throw_exception() {
        String name = "ab1";

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PlayerName(name));
    }

    @Test
    @DisplayName("한글 포함 이름 생성시 예외 발생 테스트")
    void create_name_with_korean_throw_exception() {
        String name = "가a";

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PlayerName(name));
    }
}
