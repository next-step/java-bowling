package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A2;
import static qna.domain.AnswerTest.A3;
import static qna.domain.DeleteHistoryFactory.answer;
import static qna.domain.DeleteHistoryFactory.question;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3 = new Question("title3", "contents3").writeBy(UserTest.SANJIGI);
    @DisplayName("본인 질문이 아니면 삭제 할 수 없다.")
    @Test
    public void spec01() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
          .isInstanceOf(CannotDeleteException.class)
          .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문의 모든 답변이 본인 답변이 아니면 삭제 할 수 없다.")
    @Test
    public void spec02() {
        Q1.addAnswer(A3);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
          .isInstanceOf(CannotDeleteException.class)
          .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문과 질문의 모든 답변이 본인 답변이면 삭제할 수 있다.")
    @Test
    public void spec03() throws CannotDeleteException {
        Q2.addAnswer(A2);
        Q2.addAnswer(A3);
        final List<DeleteHistory> deleteHistories = Q2.delete(UserTest.SANJIGI);

        Assertions.assertAll(
          () -> assertThat(Q2.isDeleted()).isTrue(),
          () -> assertThat(deleteHistories).containsExactly(
            question(Q2), answer(A2), answer(A3))
        );
    }
}
