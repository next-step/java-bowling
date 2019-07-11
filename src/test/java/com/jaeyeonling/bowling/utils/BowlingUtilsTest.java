package com.jaeyeonling.bowling.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingUtilsTest {

    @DisplayName("중앙 정렬 후 구분자를 추가한다.")
    @Test
    void format() {
        assertThat(BowlingUtils.format("")).isEqualTo("       |");
        assertThat(BowlingUtils.format("  ")).isEqualTo("       |");
        assertThat(BowlingUtils.format("      ")).isEqualTo("       |");
        assertThat(BowlingUtils.format("a")).isEqualTo("   a   |");
        assertThat(BowlingUtils.format("aaa")).isEqualTo("  aaa  |");
        assertThat(BowlingUtils.format("aaaaa")).isEqualTo(" aaaaa |");
        assertThat(BowlingUtils.format("aaaaaaa")).isEqualTo("aaaaaaa|");
    }
}
