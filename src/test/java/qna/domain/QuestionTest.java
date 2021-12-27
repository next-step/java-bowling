package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    @Test
    @DisplayName("답변 없는 문제 삭제")
    void delete_noAnswer() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        List<DeleteHistory> expectedDeleteHistories = Arrays.asList(
            new DeleteHistory(ContentType.QUESTION, question.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(expectedDeleteHistories);
    }

    @Test
    @DisplayName("답변 있는 문제 삭제")
    void delete_withAnswers() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answers answers = question.getAnswers();
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));

        // 문제 수 + 답변 수
        int expectedHistoryCount = 1 + answers.size();

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        Assertions.assertThat(deleteHistories.size()).isEqualTo(expectedHistoryCount);
    }

    @Test
    @DisplayName("권한 없는 문제 삭제")
    void delete_noPermissionForQuestion() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변 중 권한 없는 문제 삭제")
    void delete_noPermissionForAnswer() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answers answers = question.getAnswers();
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(1L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));

        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
