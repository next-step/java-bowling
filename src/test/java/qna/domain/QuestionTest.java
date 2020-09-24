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

    @Test
    @DisplayName("질문을 삭제한다")
    void softDeleteTest() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);

        List<DeleteHistory> deleteHistories = Q1.softDelete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).hasSize(2);
        assertThat(deleteHistories).extracting("contentId")
                .containsExactly(Q1.getId(), AnswerTest.A1.getId());
    }

    @Test
    @DisplayName("질문 삭제 시 자신이 쓴 질문이 아니면 예외가 발생한다.")
    void checkIsOwnerTest() {
        assertThatThrownBy(() -> Q1.softDelete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
    @Test
    @DisplayName("질문 삭제 시 자신이 쓰지 않은 답변이 있으면 예외가 발생한다.")
    void checkIsOwnerOfAnswerTest() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.softDelete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
