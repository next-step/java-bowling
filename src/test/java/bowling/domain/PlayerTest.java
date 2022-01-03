package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.PlayerLengthOutOfBoundException;
import bowling.exception.PlayerNameNullPointerException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"KDH", "KCY", "YSJ"})
    void 생성(String name) throws PlayerLengthOutOfBoundException {
        assertThat(new Player(name)).isInstanceOf(Player.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"KD", "DONG", "YEAST"})
    void 지정된이름길이가아닌경우(String name) {
        assertThatExceptionOfType(PlayerLengthOutOfBoundException.class)
            .isThrownBy(() -> new Player(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 이름이널혹은공백인경우(String name) {
        assertThatExceptionOfType(PlayerNameNullPointerException.class)
            .isThrownBy(() -> new Player(name));
    }

}
