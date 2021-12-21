package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    @DisplayName("길이 에러발생")
    public void checkLength() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Player("text");

            fail("길이 에러가 발생해야 한다.");
        });
    }

    @Test
    @DisplayName("언어 에러발생")
    public void checkLanguage() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Player("텍스트");

            fail("영문 에러가 발생해야 한다.");
        });
    }
}
