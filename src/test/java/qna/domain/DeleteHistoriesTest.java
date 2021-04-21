package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.TestFixture;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    @Test
    @DisplayName("기본생성자로 생성된 DeleteHistories는 서로 같은 상태다.")
    void create() {
        assertThat(new DeleteHistories()).isEqualTo(new DeleteHistories());
    }

    @Test
    @DisplayName("기본생성자로 생성된 DeleteHistories와 빈 List로 생성된 것은 서로 같은 상태다.")
    void createWithEmptyList() {
        assertThat(new DeleteHistories()).isEqualTo(new DeleteHistories(new ArrayList<>()));
    }

    @Test
    @DisplayName("DeleteHistory를 추가할 수 있다.")
    void add() {
        // given
        final DeleteHistories deleteHistories = new DeleteHistories();
        final DeleteHistory deleteHistory = DeleteHistory.createQuestionHistory(1L, TestFixture.JAVAJIGI);

        // when
        deleteHistories.add(deleteHistory);

        // then
        assertThat(deleteHistories.deleteHistories()).hasSize(1).contains(deleteHistory);
    }
}
