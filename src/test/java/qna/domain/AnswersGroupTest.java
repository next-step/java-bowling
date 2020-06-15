package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class AnswersGroupTest {

    @DisplayName("객체 정상 생성 테스트")
    @Test
    public void makeAnswersGroup_정상() {
        List<Answer> answers = new ArrayList<>();
        assertThatCode(() -> {
            AnswersGroup.of(answers);
        }).doesNotThrowAnyException();
    }
}
