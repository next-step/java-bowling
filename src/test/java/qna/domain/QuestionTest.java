package qna.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("질문의 작성자는 질문을 삭제할수있다.")
    void can_delete_question_by_writer() {
        assertThatCode(() -> Q1.delete(UserTest.JAVAJIGI))
            .doesNotThrowAnyException();
    }


    @Test
    @DisplayName("질문의 작성자외에는  질문을 삭제할수없다.")
    void cannot_delete_question_by_writer() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
