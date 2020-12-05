package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    private User user;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() {
        user = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        question = new Question("title1", "contents1").writeBy(user);
        answer = new Answer(user, question, "Answers Contents1");
    }

    @Test
    @DisplayName("질문 삭제 성공")
    public void deleteSelf() throws CannotDeleteException {
        LocalDateTime now = LocalDateTime.now();

        DeleteHistories deleteHistories = question.deleteSelf(user, now);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Collections.singletonList(DeleteHistory.from(question, now)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }

    @Test
    @DisplayName("질문 삭제 성공 - 답변자가 자기자신만 있는 경우")
    public void deleteSelf_answererSame() throws CannotDeleteException {
        question.addAnswer(answer);
        LocalDateTime now = LocalDateTime.now();

        DeleteHistories deleteHistories = question.deleteSelf(user, now);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Arrays.asList(DeleteHistory.from(question, now), DeleteHistory.from(answer, now)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }

    @Test
    @DisplayName("질문 삭제 실패 - 자신의 질문이 아닌 경우")
    public void deleteSelf_notOwner() {
        Question question = new Question("title2", "contents2").writeBy(user);
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> {
            question.deleteSelf(new User(), now);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 삭제 실패 - 다른 사람 답변이 존재하는 경우")
    public void deleteSelf_includeOtherAnswer() {
        Answer answer = new Answer(new User(), question, "Answers Contents1");
        question.addAnswer(answer);
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> {
            question.deleteSelf(user, now);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
