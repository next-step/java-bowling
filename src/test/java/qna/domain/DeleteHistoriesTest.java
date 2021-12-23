package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteHistoriesTest {
    @Test
    public void 생성자_비교() {
        List<DeleteHistory> deleteHistoryGroup =  Arrays.asList(
                DeleteHistoryTest.D10, DeleteHistoryTest.D11, DeleteHistoryTest.D12
        );
        DeleteHistories deleteHistories = new DeleteHistories(deleteHistoryGroup);
        assertThat(deleteHistories.getDeleteHistories()).isEqualTo(deleteHistoryGroup);
    }

    @Test
    public void 삭제이력_조회() {
        List<DeleteHistory> deleteHistoryGroup =  Arrays.asList(
                DeleteHistoryTest.D10, DeleteHistoryTest.D11, DeleteHistoryTest.D12
        );
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);
        question.addAnswer(AnswerTest.A2);
        assertThat(new DeleteHistories(deleteHistoryGroup)).isEqualTo(DeleteHistories.of(question, DeleteHistoryTest.DEFAULT_TIME));
    }
}