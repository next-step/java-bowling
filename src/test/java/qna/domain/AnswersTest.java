package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    @DisplayName("answers 생성 테스트")
    void create() {
        List<Answer> answerList = Arrays.asList();
        Answers answers = new Answers(answerList);
        assertThat(answers).isEqualTo(new Answers(answerList));
    }
}
