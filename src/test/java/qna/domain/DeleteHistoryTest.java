package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.QuestionTest.Q2;

public class DeleteHistoryTest {

    @Test
    @DisplayName("삭제 시, 삭제 기록이 정상적으로 포함되는지 테스트")
    public void delete_history_contain_test() throws CannotDeleteException {
        Q2.addAnswer(AnswerTest.A2);
        List<DeleteHistory> deleteHistories = Q2.delete(UserTest.SANJIGI);
        assertThat(deleteHistories).contains(new DeleteHistory(Q2));
    }
}
