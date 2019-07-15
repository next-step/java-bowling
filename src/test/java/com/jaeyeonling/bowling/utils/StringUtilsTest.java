package com.jaeyeonling.bowling.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @DisplayName("중앙 정렬을 한다.")
    @Test
    void alignCenter() {
        assertThat(StringUtils.alignCenter("aaa", 5)).isEqualTo(" aaa ");
    }
}
