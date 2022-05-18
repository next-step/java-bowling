package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("현재 유저가 작성자이면 질문을 삭제할 수 있다.")
    void deleteQuestionTest() throws CannotDeleteException {
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        List<DeleteHistory> deleteHistories = Q1.deleteQuestionAndAnswers(UserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(deleteHistories).hasSize(2),
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(Q1.getAnswers().get(0).isDeleted()).isTrue()
        );
    }

    @Test
    @DisplayName("답변의 작성자가 현재 유저가 아니면 CannotDeleteException 발생")
    void deleteQuestionWhichHasAnswersTest() {
        Answer answer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        assertThatThrownBy(() -> Q1.deleteQuestionAndAnswers(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 유저가 작성자가 아니면 CannotDeleteException 발생")
    void deleteQuestionWhichHasDifferentUser() {
        assertThatThrownBy(() -> Q1.deleteQuestionAndAnswers(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
