package bowling.domain;

import bowling.exception.InvalidPlayersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayersTest {

    @DisplayName("참가자 중 3글자 이상 Exception 확인")
    @Test
    void InvalidPlayersNameExceptionTest() {
        assertThatExceptionOfType(InvalidPlayersException.class)
                .isThrownBy(() -> {
                    Players players = Players.of(Arrays.asList("ESE", "SYDD", "HAS", "PJS"));
                }).withMessageMatching("참가자 이름은 반드시 3자 이내 이어야 합니다 : SYDD");
    }
}
