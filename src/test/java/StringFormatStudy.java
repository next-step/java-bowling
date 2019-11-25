import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by yusik on 2019/11/21.
 */
class StringFormatStudy {

    @DisplayName("escape percent(%)")
    @Test
    void escapePercent() {

        // given
        String expected = "%6s";

        // when
        String result = String.format("%%%ds", 6);
        System.out.println(result);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("왼쪽 0 추가")
    @Test
    void printLeftPadding() {

        // given
        String expected1 = "01";
        String expected2 = "12";
        String expected3 = "001";

        // when
        String result1 = String.format("%02d", 1);
        String result2 = String.format("%02d", 12);
        String result3 = String.format("%03d", 1);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        // then
        assertThat(result1).isEqualTo(expected1);
        assertThat(result2).isEqualTo(expected2);
        assertThat(result3).isEqualTo(expected3);
    }

    @DisplayName("패딩 생성")
    @Test
    void printRightPadding() {

        // given
        int length = 3;
        String expected = "   ";

        // when
        String result = String.format("%" + length + "s", "");
        System.out.println(result);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
