package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @Test
    @DisplayName("Answers 생성자 테스트")
    void constructor() {
        assertThat(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)))
                .isEqualTo(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)));
    }
}
