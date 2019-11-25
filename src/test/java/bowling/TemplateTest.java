package bowling;

import bowling.view.ResultView;
import bowling.view.Template;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TemplateTest {

    @Test
    @DisplayName("템플릿 전역 변수 생성 테스트")
    void templateStaticVariable() {
        Template.register("KSJ");
        ResultView.printTemplate();
    }
}
