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
    void create(String name) throws PlayerLengthOutOfBoundException {
        assertThat(new Player(name)).isInstanceOf(Player.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"KD", "DONG", "YEAST"})
    void lengthException(String name) {
        assertThatExceptionOfType(PlayerLengthOutOfBoundException.class)
            .isThrownBy(() -> new Player(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptyException(String name) {
        assertThatExceptionOfType(PlayerNameNullPointerException.class)
            .isThrownBy(() -> new Player(name));
    }

}
