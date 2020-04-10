package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.TestConstant.*;

public class QuestionTest {
    private Question q1 = new Question(1, "title1", "contents1").writeBy(JAVAJIGI);

    @Test
    void delete() {
        q1.addAnswer(A1);

        List<DeleteHistory> deleteHistories = q1.delete();

        assertThat(deleteHistories).hasSize(2);
    }

    @Test
    void checkAnswersRemovable() throws CannotDeleteException {
        q1.addAnswer(A1);

        q1.checkAnswersRemovable();
    }

    @Test
    void checkAnswersRemovable_cannotDeleteException() {
        q1.addAnswer(A1);
        q1.addAnswer(A2);

        assertThatThrownBy(() -> q1.checkAnswersRemovable())
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
