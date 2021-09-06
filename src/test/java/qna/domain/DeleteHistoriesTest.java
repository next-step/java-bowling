package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteHistoriesTest {

    @DisplayName("List<DeleteHistory> 일급컬렉션 생성")
    @Test
    void create() throws CannotDeleteException {
        DeleteHistory deleteHistory = AnswerTest.A1.delete(UserTest.JAVAJIGI);
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(deleteHistory);

        assertThat(deleteHistories.values()).isEqualTo(Arrays.asList(deleteHistory));
    }

}