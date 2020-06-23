package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제할 권한 확인 - 성공")
    @Test
    void checkOwner_성공() {
        assertThatCode(() -> A1.checkOwner(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> A2.checkOwner(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("삭제할 권한 확인 - 실패")
    @Test
    void checkOwner_실패() {
        assertThatThrownBy(() -> {
            A1.checkOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

        assertThatThrownBy(() -> {
            A2.checkOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("삭제 히스토리 생성 체크")
    @Test
    void checkDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(A1.delete());
        assertThat(deleteHistories.size()).isEqualTo(1);
        assertThat(deleteHistories.get(0)).isInstanceOf(DeleteHistory.class);
    }

    @DisplayName("삭제 상태 확인")
    @Test
    void checkDelete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(A1.delete());
        assertThat(A1.isDeleted()).isTrue();
    }

}
