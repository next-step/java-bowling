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

    private User javajigi;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() {
        javajigi = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        question = new Question("title1", "contents1").writeBy(javajigi);
        answer = new Answer(javajigi, question, "Answers Contents1");
    }

    @Test
    @DisplayName("질문 삭제 성공")
    public void deleteSelf() throws CannotDeleteException {
        LocalDateTime now = LocalDateTime.now();

        DeleteHistories deleteHistories = question.deleteSelf(javajigi, now);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Collections.singletonList(DeleteHistory.from(question, now)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }

    @Test
    @DisplayName("질문 삭제 성공 - 답변자가 자기자신만 있는 경우")
    public void deleteSelf_answererSame() throws CannotDeleteException {
        question.addAnswer(answer);
        LocalDateTime now = LocalDateTime.now();

        DeleteHistories deleteHistories = question.deleteSelf(javajigi, now);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Arrays.asList(DeleteHistory.from(question, now), DeleteHistory.from(answer, now)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }

    @Test
    @DisplayName("질문 삭제 실패 - 자신의 질문이 아닌 경우")
    public void deleteSelf_notOwner() {
        User anotherUser = new User();
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> {
            question.deleteSelf(anotherUser, now);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 삭제 실패 - 다른 사람 답변이 존재하는 경우")
    public void deleteSelf_includeOtherAnswer() {
        Answer sanjigi = new Answer(new User(), question, "Answers Contents1");
        question.addAnswer(sanjigi);
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> {
            question.deleteSelf(javajigi, now);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
