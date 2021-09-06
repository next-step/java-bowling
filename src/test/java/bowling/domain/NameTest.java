package bowling.domain;

import bowling.domain.exception.NameLengthExceededException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

    @Test
    @DisplayName("생성")
    public void create() {
        assertDoesNotThrow(() -> new Name("PJS").equals(new Name("PJS")));
    }

    @Test
    @DisplayName("이름 길이 초과")
    public void createNameLengthExceeded_exception() {
        assertThrows(NameLengthExceededException.class, () -> new Name("ABCDE"));
    }

}