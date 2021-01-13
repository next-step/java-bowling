package domain;

import bowling.domain.Chance;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChanceTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3, -4, -100, 100, 11, 12, 13, 15, 36})
    public void check_넘어진_핀의_갯수_테스트(int paramData) {
        Assertions.assertThatThrownBy(() -> {
            Chance chance = new Chance(paramData);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void check_넘어진_핀의_갯수가_10개_이하인지() {
        Chance chance = new Chance();
        assertThat(true).isEqualTo(chance.getCountOfPin() <= 10);
    }
}
