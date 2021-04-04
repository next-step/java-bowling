package bowling.domain;

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
    void create_english_name_length_more_than_3_throw_exception() {
        String name = "tess";

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PlayerName(name));
    }

    @Test
    void create_name_with_number_throw_exception() {
        String name = "ab1";

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PlayerName(name));
    }

    @Test
    void create_name_with_korean_throw_exception() {
        String name = "가a";

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PlayerName(name));
    }
}
