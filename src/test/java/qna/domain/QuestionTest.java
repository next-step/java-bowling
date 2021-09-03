package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    @Test
    @DisplayName("삭제 (상태값 변경)")
    void delete() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        // when
        question.delete(UserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제시 Answer 까지 삭제")
    void deleteEvenTheAnswerDelete() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);

        // when
        question.delete(UserTest.JAVAJIGI);

        // then
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 후 DeleteHistories 개수 확인")
    void deleteReturnDeleteHistoriesCount() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);

        // when
        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI).getDeleteHistories();

        // then
        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제시 본인글이 아니라면 Exception")
    void deleteNotOwnerException() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        // when
        // then
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제시 다른 사람의 답변이 있다면 Exception")
    void deleteOtherUserAnswerException() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A2);

        // when
        // then
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
