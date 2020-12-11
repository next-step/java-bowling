package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.SANJIGI;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 작성자가 아닐경우 CannotDeleteException")
    @Test
    void throwExceptionIfNotQuestionOwner() {
        assertThatThrownBy(() -> Q1.validateQuestionOwner(SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("삭제 메서드 실행 시 question 삭제 플래그 true, DeleteHistories 객체 반환")
    @Test
    void deleteQuestion() throws Exception {
        DeleteHistories deleteResult = Q2.delete(SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(deleteResult).isEqualTo(new DeleteHistories(Arrays.asList(new DeleteHistory(ContentType.QUESTION, Q2.getId(), SANJIGI, LocalDateTime.now()))));
    }
}
