package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_성공() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        List<DeleteHistory> deleteHistoryList = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistoryList).hasSize(1);
    }

    @Test
    void delete_다른_사람이_쓴_글() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_질문자_와_답변자_같음() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.ofUser(11L, UserTest.JAVAJIGI));

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void delete_질문자_와_답변자_다름() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.ofUser(12L, UserTest.SANJIGI));
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
