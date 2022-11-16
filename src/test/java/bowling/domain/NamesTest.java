package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class NamesTest {

    private List<Name> createNames(String... names) {
        return Arrays.stream(names)
                .map(Name::new)
                .collect(Collectors.toList());
    }

    @Test
    void validate_size_fail() {
        assertThatThrownBy(() -> new Names(List.of())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validate_size_success() {
        assertDoesNotThrow(() -> new Names(List.of(new Name("cys"))));
    }

    @Test
    void names() {
        Names names = new Names(createNames("cys", "pjh", "aka"));

        assertThat(names.names()).containsExactly(new Name("cys"), new Name("pjh"), new Name("aka"));
    }
}
