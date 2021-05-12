package bowling.util;

import bowling.domain.Name;
import org.junit.jupiter.api.Test;

import static bowling.domain.frame.FramesTest.lastFrames;
import static bowling.util.StringUtils.convertFrames;
import static bowling.util.StringUtils.convertName;

public class StringUtilsTest {

    @Test
    void 이름확인() {
        System.out.println(convertName(Name.of("확인용")));
    }

    @Test
    void 프레임확인() {
        System.out.println(convertFrames(lastFrames()));
    }

    @Test
    void 길이확인() {
        System.out.println(String.format(" %3s  |", "1"));
        System.out.println(String.format("  %s  |", "20"));
    }
}
