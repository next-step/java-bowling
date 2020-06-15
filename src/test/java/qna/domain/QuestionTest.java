package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question(1L, "title1", "contents1").writeBy(JAVAJIGI)
            .setAnswers(new Answers(new ArrayList<>()));
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI)
            .setAnswers(new Answers(Collections.singletonList(
                    new Answer(JAVAJIGI, new Question(), "Answers Contents2"))));

    @DisplayName("답변이 없고 로그인한 사용자가 올린 질문 삭제 시 삭제 성공")
    @Test
    public void deleteQuestionTest() throws CannotDeleteException {
        assertThat(Q1.isDeleted()).isFalse();

        Q1.delete(JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("삭제 시 삭제한 질문 반환")
    @Test
    public void returnHistoryAfterDelete() {
        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);

        assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.QUESTION, 1L, JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("로그인한 사용자와 질문자가 다른 상태에서 삭제 시도 시 예외 발생 - CannotDeleteException")
    @Test
    public void deleteQuestionsAuthTest() {
        assertThatThrownBy(() -> Q1.delete(SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제 요청한 질문에 질문자 외의 답변자가 답변을 단 경우 삭제 실패 - CannotDeleteException")
    @Test
    public void deleteFailWhenHaveAnswerOfOtherPeople() {
        assertThatThrownBy(() -> Q2.delete(SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
