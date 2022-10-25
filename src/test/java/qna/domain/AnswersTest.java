package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("일급 컬렉션 내 답글을 삭제 처리할 수 있다.")
    @Test
    void deleteAll() {
        Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(List.of(answer1, answer2));

        answers.delete(UserTest.JAVAJIGI);

        assertAll(
                () -> assertTrue(answer1.isDeleted()),
                () -> assertTrue(answer2.isDeleted())
        );
    }

    @DisplayName("삭제된 답글의 정보를 리스트로 받을 수 있다.")
    @Test
    void delete() {
        Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(List.of(answer1, answer2));
        DeleteHistory deleteHistory1 = new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now());
        DeleteHistory deleteHistory2 = new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now());

        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).containsExactly(deleteHistory1, deleteHistory2);
    }
}
