package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NameTest {

    @DisplayName("The length of name must be under 3")
    @Test
    void testNameUnderThreeCharacters() {
        assertThatThrownBy(() -> new Name("tomo")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Name must be composed of alphabets")
    @Test
    void testNameComposedOfAlphabets() {
        assertThatThrownBy(() -> new Name("토모")).isInstanceOf(IllegalArgumentException.class);
    }
}
