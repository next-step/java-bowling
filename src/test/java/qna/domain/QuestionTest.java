package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    Question question;

    @BeforeEach
    void setUp() {
        question = Q1;
    }

    @Test
    @DisplayName("삭제 (상태값 변경)")
    void delete() {
        // given
        // when
        question.delete(UserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제시 본인글이 아니라면 Exception")
    void deleteNotOwnerException() {
        // given
        // when
        // then
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제시 다른 사람의 답변이 있다면 Exception")
    void deleteOtherUserAnswerException() {
        // given
        question.addAnswer(AnswerTest.A2);

        // when
        // then
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
