package qna.domain;

import org.junit.jupiter.api.*;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제할 때 deleteHistory 에 (삭제할) 질문과 답변이 쌓인다.")
    @Order(0)
    @Test
    void delete_addDeleteHistories() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories.values().size()).isEqualTo(2);
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 불가능")
    @Order(1)
    @Test
    void delete_isOwner_error() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(UserTest.SANJIGI));
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능")
    @Order(2)
    @Test
    void delete_isOwner() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("답변글이 없으면 삭제가능")
    @Order(3)
    @Test
    void delete_deleteNoAnswers() throws CannotDeleteException {
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 다른 경우 삭제가 가능")
    @Order(4)
    @Test
    void delete_deleteAnswers() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 다른 경우 삭제가 불가능")
    @Order(5)
    @Test
    void delete_deleteAnswers_error() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(UserTest.JAVAJIGI));
    }

}
