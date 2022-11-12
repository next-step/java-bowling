package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A2;
import static qna.domain.QuestionTest.*;

public class AnswersTest {

    @Test
    void deleteAnswers() {
        Answers answers = Q1.getAnswers();

        Q1.addAnswer(A2);
        answers.delete();
        assertThat(A2.isDeleted()).isTrue();
    }

}
