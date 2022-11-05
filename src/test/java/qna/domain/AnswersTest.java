package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

class AnswersTest {
    @DisplayName("답변 소유자 외에 다른 사람이 작성한 답변이 없다면 답변 삭제상태를 true 로 바꾸고 삭제 히스토리를 리턴한다.")
    @Test
    void delete() throws CannotDeleteException {
        User owner = user(123L);
        List<Answer> answers = List.of(new Answer(owner), new Answer(owner));
        
        assertThat(new Answers(answers).delete(owner))
                .containsExactlyElementsOf(List.of(
                        new DeleteHistory(ContentType.ANSWER, answers.get(0).getId(), answers.get(0).getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, answers.get(1).getId(), answers.get(1).getWriter(), LocalDateTime.now())
                ));
        assertThat(answers).allSatisfy((Answer answer) -> answer.isDeleted());
    }

    @DisplayName("답변 소유자외에 다른 사람이 작성한 답변이 있다면 예외를 발생시킨다.")
    @Test
    void delete_when_invalid_owner() {
        User owner = user(123L);
        User other = user(321L);

        assertThatThrownBy(() -> new Answers(List.of(new Answer(owner), new Answer(other))).delete(other))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
    
    private User user(long id) {
        return new User(id, "userId", "password", "name", "email");
    }

    /*private void verifyDeleteHistories(Question question, Answer answer) {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }*/
}
