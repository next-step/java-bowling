package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 작성자와 loginUser가 같을 경우 delete 성공")
    @Test
    void delete_성공() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        List<DeleteHistory> deleteHistoryList = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistoryList).hasSize(1);
    }

    @DisplayName("질문 작성자와 loginUser가 다를 경우 delete 실패")
    @Test
    void delete_다른_사람이_쓴_글() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 작성자, 답변자가 loginUser와 같을 경우 delete 성공")
    @Test
    void delete_성공_질문자_와_답변자_같음() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.ofUser(11L, UserTest.JAVAJIGI));

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문 작성자, 답변자가 loginUser와 다를 경우 delete 실패")
    @Test
    void delete_질문자_와_답변자_다름() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.ofUser(12L, UserTest.SANJIGI));
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
