package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    @DisplayName("삭제 시 히스토리 객체를 반환한다.")
    @Test
    public void 답변_삭제_성공() throws Exception {
        // given
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        DeleteHistory deleteHistory = answer.delete(UserTest.JAVAJIGI);

        // then
        DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.ANSWER,11L, UserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
    }

    @Test
    public void 답변_삭제_실패_로그인_사용자와_답변자가_다를_때() {
        // given
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                // then
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
