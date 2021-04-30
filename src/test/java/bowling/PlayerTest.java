package bowling;

import bowling.domain.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PlayerTest {
    @Test
    void Given_Name_When_New_Then_Create() {
        String name = "ABC";

        assertThat(new Player(name)).isEqualTo(new Player(name));
    }

    @Test
    void Given_4CharName_When_New_Then_Exception() {
        String name = "ABCD";
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Player(name))
                .withMessage("이름의 길이가 잘못되었습니다");
    }

    @Test
    void Given_2CharName_When_New_Then_Exception() {
        String name = "AB";
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Player(name))
                .withMessage("이름의 길이가 잘못되었습니다");
    }
}
