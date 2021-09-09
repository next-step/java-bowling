package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {

    private Answer answerByJavajigi;

    @BeforeEach
    public void setup() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answerByJavajigi = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
    }

    @DisplayName("답변자가 아닌 경우 답변을 삭제할 수 없다.")
    @Test
    public void answerDeletePermissionTest() {
        assertThatThrownBy(() -> answerByJavajigi.deleteBy(UserTest.SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 삭제 시, 답변은 삭제 상태가 된다.")
    @Test
    public void deletedAnswerStatusTest() {
        assertThat(answerByJavajigi.isDeleted()).isFalse();
        answerByJavajigi.deleteBy(UserTest.JAVAJIGI);
        assertThat(answerByJavajigi.isDeleted()).isTrue();
    }

    @DisplayName("삭제 된 답변의 DeleteHistory 를 남길 수 있다.")
    @Test
    public void deleteHistoryTest() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(answerByJavajigi::toDeleteHistory);
        answerByJavajigi.deleteBy(UserTest.JAVAJIGI);
        assertThat(answerByJavajigi.toDeleteHistory())
                .isExactlyInstanceOf(DeleteHistory.class);
    }

}
