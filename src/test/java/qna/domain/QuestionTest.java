package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제할 권한 확인 - 성공")
    @Test
    void checkOwner_성공() {
        assertThatCode(() -> Q1.checkOwner(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> Q2.checkOwner(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("삭제할 권한 확인 - 실패")
    @Test
    void checkOwner_실패() {
        assertThatThrownBy(() -> {
            Q1.checkOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");

        assertThatThrownBy(() -> {
            Q2.checkOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("삭제 히스토리 생성 체크")
    @Test
    void checkDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        Q1.delete(deleteHistories);
        assertThat(deleteHistories.size()).isEqualTo(1);
        assertThat(deleteHistories.get(0)).isInstanceOf(DeleteHistory.class);
    }

    @DisplayName("삭제 상태 확인")
    @Test
    void checkDelete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        Q1.delete(deleteHistories);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
