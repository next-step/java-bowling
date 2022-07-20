package bowling2.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class Board2Test {

    @Test
    void init() {
        assertThat(Board2.init()).isEqualTo(new Board2(new ArrayList<>()));
    }
}
