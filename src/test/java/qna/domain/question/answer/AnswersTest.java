package qna.domain.question.answer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qna.domain.question.answer.AnswerTest.A1;
import static qna.domain.question.answer.AnswerTest.A2;

class AnswersTest {

    @Test
    @DisplayName("Answers 객체를 생성한다")
    void shouldCreate() {
        Answers answers = Answers.from(Arrays.asList(A1, A2));
        assertThat(answers).isEqualTo(Answers.from(Arrays.asList(A1, A2)));
    }
}