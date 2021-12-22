package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void 질문_삭제_성공() throws Exception {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        // when
        DeleteHistories deleteHistories = question.delete(UserTest.JAVAJIGI);

        // then
        DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.QUESTION,1L, UserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(deleteHistories.getQuestionOfDeleteHistory()).isEqualTo(expectedDeleteHistory);
    }

    @Test
    public void 질문_삭제_실패_로그인_사용자와_질문자가_다를_때() throws Exception {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        // when
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                // then
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
