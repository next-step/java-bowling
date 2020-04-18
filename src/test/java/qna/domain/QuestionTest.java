package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void deleteQuestionWithoutAnswer() {
        Question question = new Question("title1", "contents1")
            .writeBy(UserTest.JAVAJIGI);

        List<DeleteHistory> deleteHistories = question.deleteBy(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    public void deleteQuestionWithAnswer() {
        Question question = new Question("title1", "contents1")
            .writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.answerBy(UserTest.JAVAJIGI));

        List<DeleteHistory> deleteHistories = question.deleteBy(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    public void deleteByAnotherUser() {
        Question question = new Question("title1", "contents1")
            .writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.answerBy(UserTest.JAVAJIGI));

        assertThatThrownBy(() -> question.deleteBy(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteQuestionWithAnswerOfAnotherUser() {
        Question question = new Question("title1", "contents1")
            .writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.answerBy(UserTest.SANJIGI));

        assertThatThrownBy(() -> question.deleteBy(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
