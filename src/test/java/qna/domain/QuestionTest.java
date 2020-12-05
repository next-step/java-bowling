package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 삭제 성공")
    public void deleteSelf() throws CannotDeleteException {
        LocalDateTime now = LocalDateTime.now();

        DeleteHistories deleteHistories = Q1.deleteSelf(JAVAJIGI, now);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Arrays.asList(DeleteHistory.from(Q1, now), DeleteHistory.from(A1, now)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }

    @Test
    @DisplayName("질문 삭제 성공 - 답변자가 자기자신만 있는 경우")
    public void deleteSelf_answererSame() throws CannotDeleteException {
        Q1.addAnswer(A1);
        LocalDateTime now = LocalDateTime.now();

        DeleteHistories deleteHistories = Q1.deleteSelf(JAVAJIGI, now);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Arrays.asList(DeleteHistory.from(Q1, now), DeleteHistory.from(A1, now)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }

    @Test
    @DisplayName("질문 삭제 실패 - 자신의 질문이 아닌 경우")
    public void deleteSelf_notOwner() {
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> {
            Q2.deleteSelf(JAVAJIGI, now);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 삭제 실패 - 다른 사람 답변이 존재하는 경우")
    public void deleteSelf_includeOtherAnswer() {
        Q1.addAnswer(A2);
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> {
            Q1.deleteSelf(JAVAJIGI, now);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
