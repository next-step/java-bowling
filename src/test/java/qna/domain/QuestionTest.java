package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import qna.CannotDeleteException;
import qna.service.DeleteHistoryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    @Mock
    private DeleteHistoryService deleteHistoryService;

    @Test
    @DisplayName("본인이쓰지않은 글 삭제 불가 테스트")
    public void tryOtherUserQuestionTest() {
        assertThatThrownBy(() -> {
            Q1.checkUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("데이터상태 deleted 처리 테스트 ")
    public void deleteStatusTest() {
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("본인이 쓴글 지우는 테스트")
    public void tryUserSelfQuestionTest() throws CannotDeleteException {
        Q1.checkUser(UserTest.JAVAJIGI);
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제히스토리저장테스트")
    public void historyTest() {
        DeleteHistories deleteHistories = new DeleteHistories();
        Q1.deleteQuestionHistory(deleteHistories);
        assertThat(deleteHistories.size()).isEqualTo(1);
    }
}
