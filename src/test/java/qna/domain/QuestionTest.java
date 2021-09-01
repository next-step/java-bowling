package qna.domain;


import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 삭제")
    void delete() throws CannotDeleteException {
        //given
        Q1.deleteBy(UserTest.JAVAJIGI);

        //when
        boolean actual = Q1.isDeleted();

        //then
        assertThat(actual).isTrue();

    }

    @Test
    @DisplayName("삭제 가능 여부 - 질문 작성자 일치")
    void deletable() throws CannotDeleteException {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        //when

        //then
        assertDoesNotThrow(() -> question.deletable(UserTest.JAVAJIGI));

    }

    @Test
    @DisplayName("삭제 가능 여부 - 질문 작성자 불일치")
    void deletable_mismatch() throws CannotDeleteException {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        //when
        ThrowableAssert.ThrowingCallable actual = () -> question.deletable(UserTest.SANJIGI);

        //then
        assertThatThrownBy(actual)
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");

    }

    @Test
    @DisplayName("삭제 가능 여부 - 질문 작성자 and 답변 작성자 일치")
    void deletable_with_answers() throws CannotDeleteException {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswers(AnswersTest.ANSWERS1);

        //when

        //then
        assertDoesNotThrow(() -> question.deletable(UserTest.JAVAJIGI));

    }

    @Test
    @DisplayName("삭제 가능 여부 - 질문 작성자 and 답변 작성자 불일치")
    void deletable_with_answers_mismatch() {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswers(AnswersTest.ANSWERS2);

        //when
        ThrowableAssert.ThrowingCallable actual = () -> question.deletable(UserTest.JAVAJIGI);

        //then
        assertThatThrownBy(actual)
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    }

}
