package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 검증() {
        assertThatThrownBy(() -> Q1.validateDelete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 삭제_question_and_answer() {
        Question q = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        q.addAnswer(AnswerTest.A1);
        q.addAnswer(AnswerTest.A2);

        q.deleteQuestionAndAnswers();
        assertThat(q.getAnswers().isDeletedAll()).isTrue();
        assertThat(q.isDeleted()).isTrue();
    }
}
