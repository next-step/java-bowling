package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.ContentType.*;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_다른_사람질문_삭제시_예외() {
        Question question = new Question("title1", "contents1")
                .writeBy(UserTest.JAVAJIGI);

        assertThatThrownBy(() -> question.delete(SANJIGI, new DeleteHistories(), now()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_다른_사람답변_존재하면_삭제시_예외() {
        Question question = new Question("title1", "contents1")
                .writeBy(UserTest.JAVAJIGI);
        question.addAnswer(A2);

        assertThatThrownBy(() -> question.delete(JAVAJIGI, new DeleteHistories(), now()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_정상_동작() throws CannotDeleteException {
        Question question = new Question("title1", "contents1")
                .writeBy(UserTest.JAVAJIGI);
        question.addAnswer(A1);

        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistory questionDeleteHistory = new DeleteHistory(QUESTION, Q1.getId(), JAVAJIGI, now());
        DeleteHistory answerDeleteHistory = new DeleteHistory(ANSWER, A1.getId(), JAVAJIGI, now());

        assertThat(question.isDeleted()).isFalse();
        assertThat(deleteHistories.contains(questionDeleteHistory)).isFalse();
        assertThat(deleteHistories.contains(answerDeleteHistory)).isFalse();
        question.delete(JAVAJIGI, deleteHistories, now());
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories.contains(questionDeleteHistory)).isTrue();
        assertThat(deleteHistories.contains(answerDeleteHistory)).isTrue();
    }

}
