package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    void checkRemovable() throws Exception {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));

        answers.checkRemovable(UserTest.JAVAJIGI);
    }

    @Test
    void checkRemovable_cannotDeleteException() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThatThrownBy(() -> answers.checkRemovable(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void delete() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));

        answers.delete();
    }

    @Test
    void getDeleteHistories() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));

        List<DeleteHistory> deleteHistoryList = answers.getDeleteHistories();

        assertThat(deleteHistoryList).hasSize(1);
    }
}
