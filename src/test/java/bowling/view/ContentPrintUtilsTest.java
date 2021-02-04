package bowling.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static bowling.view.ContentPrintUtils.repeatedCharacterString;
import static org.assertj.core.api.Assertions.assertThat;

class ContentPrintUtilsTest {

    @Test
    @DisplayName("좌우 보딩(빈 칸) 추가")
    void fillContentBoarding() {
        String input = "ABC";
        String expected = " ABC ";

        assertThat(ContentPrintUtils.fillContentBoarding(input))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("좌우 마진 추가(컨텐츠 크기보다 원하는 길이가 길 때)")
    void fillBothSideMargin() {
        String input = "sample";
        int expectedLength = 10;

        String expected = ContentPrintUtils.fillBothSideMargin(input, expectedLength);

        assertThat(expected.length())
                .isEqualTo(10);
    }

    @Test
    @DisplayName("좌우 마진 추가 시 무시하는 케이스")
    void ignoreFillMargin() {
        String input = "sample";
        int expectedLength = 4;

        String expected = ContentPrintUtils.fillBothSideMargin(input, expectedLength);

        assertThat(expected.length())
                .isEqualTo(input.length());
    }

    @Test
    @DisplayName("String 리스트에 빈 컨텐츠 추가")
    void fillEmptyFrameContent() {
        int expectingFrameSize = 10;
        List<String> input = ContentPrintUtils.fillEmptyFrameContent(Arrays.asList("temp", "anything", "1", "2", "3"), expectingFrameSize);

        assertThat(input.size())
                .isEqualTo(expectingFrameSize);
    }

    @Test
    void repeatCharacter() {
        char inputCharacter = '1';
        int repeatTime = 4;

        assertThat(repeatedCharacterString(inputCharacter, repeatTime))
                .isEqualTo("1111");
    }
}
