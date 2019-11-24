package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    @Test
    void addBlank() {
        int totalLength = 6;
        assertThat(StringUtils.addBlank("X", totalLength)).isEqualTo("   X  ");
        assertThat(StringUtils.addBlank("1|", totalLength)).isEqualTo("  1|  ");
        assertThat(StringUtils.addBlank("1|/", totalLength)).isEqualTo("  1|/ ");
        assertThat(StringUtils.addBlank("name", totalLength)).isEqualTo(" name ");
    }
}
