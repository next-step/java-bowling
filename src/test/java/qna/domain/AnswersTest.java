package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.TestConstant.*;

public class AnswersTest {
    @Test
    void checkRemovable() throws Exception {
        Answers answers = new Answers(Arrays.asList(A1));

        answers.checkRemovable(JAVAJIGI);
    }

    @Test
    void checkRemovable_cannotDeleteException() {
        Answers answers = new Answers(Arrays.asList(A1, A2));

        assertThatThrownBy(() -> answers.checkRemovable(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void delete() {
        Answers answers = new Answers(Arrays.asList(A1));

        List<DeleteHistory> deleteHistories = answers.delete();

        assertThat(deleteHistories).hasSize(1);
    }
}
