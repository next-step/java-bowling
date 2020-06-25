package bowling.player.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("Player는 이름으로 구분한다.")
    @ParameterizedTest
    @CsvSource({"lmh, true", "lmq, false"})
    void playerEqualsName(String name, boolean expectedResult) {
        Player player = new Player("lmh");

        assertThat(player.equals(new Player(name))).isEqualTo(expectedResult);
    }
}