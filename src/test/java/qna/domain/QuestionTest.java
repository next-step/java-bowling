package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 삭제_실패() {
        assertThatThrownBy(() -> Q1.deleteQuestionAndAnswers(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 삭제_성공_답변있음() throws CannotDeleteException {
        Question q = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        q.addAnswer(AnswerTest.A1);
        q.addAnswer(AnswerTest.A1);

        q.deleteQuestionAndAnswers(q.getWriter());
        assertThat(q.isDeleted()).isTrue();
    }

    @Test
    void 삭제_성공_답변없음() throws CannotDeleteException {
        Question q = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        q.deleteQuestionAndAnswers(q.getWriter());
        assertThat(q.isDeleted()).isTrue();
    }

    @Test
    void 생성() throws CannotDeleteException {
        Question q = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        q.addAnswer(AnswerTest.A1);
        q.addAnswer(AnswerTest.A1);

        DeleteHistories deleteHistories = q.deleteQuestionAndAnswers(UserTest.JAVAJIGI);
        assertThat(deleteHistories).hasSize(3);
    }
}
