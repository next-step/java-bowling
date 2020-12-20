package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("다른 사람이 작성한 질문을 삭제하면 예외 발생테스트")
    @Test
    void questionDeleteExceptionTest() throws CannotDeleteException {
        assertThatThrownBy(
                () -> Q1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 삭제 테스트")
    @Test
    void questionDeleteTest() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).isNotNull()
        );
    }


}
