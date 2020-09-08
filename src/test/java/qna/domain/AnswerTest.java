package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static qna.domain.QuestionTest.Q1;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents3");

    @Test
    public void delete_정상적으로_삭제처리되어_상태가_변하는지_확인() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI, DeleteHistories.create(new ArrayList()));
        assertTrue(A1.isDeleted());
    }

    @Test
    public void delete_작성자와_요청자가_다를때_확인() {
        assertThatThrownBy(() -> {
            A1.delete(UserTest.SANJIGI, DeleteHistories.create(new ArrayList()));
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_삭제이력_추가_확인() throws CannotDeleteException {
        DeleteHistories deleteHistories = A1.delete(UserTest.JAVAJIGI, DeleteHistories.create(new ArrayList()));
        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }
}
