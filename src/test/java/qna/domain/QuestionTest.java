package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제에 성공하면 답변 삭제 히스토리를 포함한 질문 히스토리 리스트를 반환한다.")
    @Test
    void deleteQuestion() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @DisplayName("본인의 질문이 아니면 CannotDeleteException Throw")
    @Test
    void deleteQuestionThrowException() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
