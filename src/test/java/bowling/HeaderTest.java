package bowling;

import bowling.view.ResultView;
import bowling.domain.Header;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HeaderTest {

    @Test
    @DisplayName("템플릿 전역 변수 생성 테스트")
    void templateStaticVariable() {
        Header.register("KSJ");
        ResultView.printFrames(new ArrayList<>());
    }
}
