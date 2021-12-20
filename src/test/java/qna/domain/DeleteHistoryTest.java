package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("삭제 이력을 남기기 위한 테스트")
public class DeleteHistoryTest {
    public static final DeleteHistory DH1 = new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DH2 = new DeleteHistory(ContentType.ANSWER, 2L, UserTest.SANJIGI, LocalDateTime.now());

    @DisplayName("Question의 삭제 이력 생성")
    @Test
    void createFromQuestionTest() {
        // when
        DeleteHistory deleteHistory = DeleteHistory.from(QuestionTest.Q1);
        // then
        assertThat(deleteHistory.contentId()).isEqualTo(QuestionTest.Q1.getId());
        assertThat(deleteHistory.deletedBy()).isEqualTo(QuestionTest.Q1.getWriter());
    }

    @DisplayName("Answer의 삭제 이력 생성")
    @Test
    void createFromAnswerTest() {
        // when
        DeleteHistory deleteHistory = DeleteHistory.from(AnswerTest.A1);
        // then
        assertThat(deleteHistory.deletedBy()).isEqualTo(AnswerTest.A1.getWriter());
    }

    @DisplayName("삭제 이력 대상 콘텐트 Id 확인")
    @Test
    void contentIdTest() {
        // then
        assertThat(DH1.contentId()).isEqualTo(1L);
    }

    @DisplayName("삭제 이력 생성자 반환")
    @Test
    void deletedByTest() {
        // then
        assertThat(DH1.deletedBy()).isEqualTo(UserTest.JAVAJIGI);
    }
}
