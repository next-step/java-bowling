package bowling.domain;

import bowling.exception.OutOfBoundNameSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("유저 생성")
    void createPlayer(){
        Player player = Player.from("LJH");

        assertThat(Player.from("LJH"))
                .isEqualTo(player);
    }

    @Test
    @DisplayName("유저 생성 이름 길이 예외")
    void player_exception() {
        assertThatExceptionOfType(OutOfBoundNameSize.class)
                .isThrownBy(() -> {
                    Player.from("1234");
                }).withMessageMatching("참가자의 이름은 3글자로 입력해 주세요.");
    }
}
