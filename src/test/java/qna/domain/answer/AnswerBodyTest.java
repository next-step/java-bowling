package qna.domain.answer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import qna.domain.question.QuestionTest;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerBodyTest {

    @Nested
    class toQuestion_메서드는 {

        @Test
        void 질문을_설정한다() {
            AnswerBody answerBody = new AnswerBody(QuestionTest.Q1, null);

            answerBody.toQuestion(QuestionTest.Q2);

            assertThat(answerBody).isEqualTo(new AnswerBody(QuestionTest.Q2, null));
        }
    }
}
