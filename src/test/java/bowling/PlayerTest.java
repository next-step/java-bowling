package bowling;

import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void Given_Name_When_New_Then_Create() {
        String name = "ABC";

        assertThat(new Player(name)).isEqualTo(new Player(name));
    }

    @Test
    @DisplayName("4글자 이상이면 Exception")
    void Given_4CharName_When_New_Then_Exception() {
        String name = "ABCD";
        assertThatExceptionOfType(IllegalPlayerNameException.class)
                .isThrownBy(() -> new Player(name))
                .withMessage("이름의 길이가 잘못되었습니다");
    }

    @Test
    @DisplayName("2글자 이하이면 Exception")
    void Given_2CharName_When_New_Then_Exception() {
        String name = "AB";
        assertThatExceptionOfType(IllegalPlayerNameException.class)
                .isThrownBy(() -> new Player(name))
                .withMessage("이름의 길이가 잘못되었습니다");
    }

    @Test
    @DisplayName("nonamePlayer의 이름은 \"---\"")
    void Given_Noname_When_Name_Then_DashDashDash() {
        assertThat(Player.noname().name()).isEqualTo("---");
    }
}
