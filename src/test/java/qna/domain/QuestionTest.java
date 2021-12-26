package qna.domain;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Answer가 없을 때 자신이 작성한 Question이 아닌 경우 삭제할 수 없다.")
    @Test
    void cannotDeleteQuestionAnswersNotExistAndNotAuthor() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        // when
        ThrowableAssert.ThrowingCallable callable = () -> question.deleteBy(UserTest.SANJIGI);

        // then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(callable)
                .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("Answer가 없을 때 자신이 작성한 Question은 삭제할 수 있다.")
    @Test
    void canDeleteQuestionAnswersNotExist() {
        // given
        Question question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Question question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

        // when
        question1.deleteBy(UserTest.JAVAJIGI);
        question2.deleteBy(UserTest.SANJIGI);

        // then
        assertAll(
                () -> assertThat(question1.isDeleted()).isTrue(),
                () -> assertThat(question2.isDeleted()).isTrue()
        );
    }

    @DisplayName("Answer가 존재할 때 모든 Answer가 Question의 작성자가 작성한 것이 아닌 경우 Question은 삭제 불가능하다.")
    @Test
    void cannotDeleteQuestionAnswersExistAndMultipleAuthor() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);
        question.addAnswer(AnswerTest.A2);

        // when
        ThrowableAssert.ThrowingCallable callable = () -> question.deleteBy(UserTest.JAVAJIGI);

        // then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(callable)
                .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("Answer가 존재할 때 모든 Answer가 Question의 작성자가 작성한 경우 Question을 삭제할 수 있다.")
    @Test
    void canDeleteQuestionAnswerExistAndAuthorOnly() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);

        // when
        question.deleteBy(UserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }
}
