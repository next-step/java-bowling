package com.jaeyeonling.bowling.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @DisplayName("중앙 정렬을 한다.")
    @Test
    void alignCenter() {
        assertThat(StringUtils.alignCenter("", 0)).isEqualTo("");
        assertThat(StringUtils.alignCenter("", 1)).isEqualTo(" ");
        assertThat(StringUtils.alignCenter("", 2)).isEqualTo("  ");
        assertThat(StringUtils.alignCenter("", 5)).isEqualTo("     ");
        assertThat(StringUtils.alignCenter("a", 5)).isEqualTo("  a  ");
        assertThat(StringUtils.alignCenter("aa", 5)).isEqualTo("  aa ");
        assertThat(StringUtils.alignCenter("aaa", 5)).isEqualTo(" aaa ");
        assertThat(StringUtils.alignCenter("aaaaaa", 6)).isEqualTo("aaaaaa");
    }
}
