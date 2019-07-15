package com.jaeyeonling.bowling.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingUtilsTest {

    @DisplayName("중앙 정렬 한다.")
    @ParameterizedTest
    @CsvSource({
            "'','       '",
            "' ','       '",
            "'  ','       '",
            "'   ','       '",
            "'     ','       '",
            "'a','   a   '",
            "'aaa','  aaa  '",
            "'aaaaa',' aaaaa '",
    })
    void format(final String before,
                final String after) {
        assertThat(BowlingUtils.format(before)).isEqualTo(after);
    }
}
