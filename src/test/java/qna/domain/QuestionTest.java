package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final DeleteHistory Q1_DELETE_HISTORY = new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, LocalDateTime.now());

    @Test
    @DisplayName("사용자가 해당 Qna 를 삭제 가능한지 확인")
    void validateDeletableUser() throws CannotDeleteException {
        Q1.validateDeletable(UserTest.JAVAJIGI);
        Q2.validateDeletable(UserTest.SANJIGI);
    }

    @Test
    @DisplayName("사용자가 해당 Qna 를 삭제 불가능한 경우 에러")
    void exception() {
        assertThatThrownBy(() -> {
            Q1.validateDeletable(UserTest.SANJIGI);
            Q2.validateDeletable(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 삭제 이력 및 삭제 처리가 정상적으로 동작하는지 확인")
    void delete() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
        
        List<DeleteHistory> actual = question.delete();
        List<DeleteHistory> expect = List.of(Q1_DELETE_HISTORY, AnswerTest.A1_DELETE_HISOTRY, AnswerTest.A2_DELETE_HISOTRY);

        assertThat(question.isDeleted()).isTrue();
        assertThat(actual).isEqualTo(expect);
    }

}
