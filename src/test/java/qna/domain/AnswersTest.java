package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private User javajigi;
    private Question question;
    private LocalDateTime deleteDate;

    @BeforeEach
    void setUp() {
        javajigi = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        question = new Question();
        deleteDate = LocalDateTime.now();
    }

    @Test
    @DisplayName("Answers 제거 성공")
    void deleteSelf_success() throws CannotDeleteException {
        Answer answer1 = new Answer(javajigi, question, "Answers Contents1");
        Answer answer2 = new Answer(javajigi, question, "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        DeleteHistories deleteHistories = answers.deleteSelf(javajigi, deleteDate);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Arrays.asList(
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), deleteDate),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), deleteDate)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }

    @Test
    @DisplayName("Answers 제거 실패 - 다른 답변자 존재")
    void deleteSelf_failHaivingOtherAnswerWriter() {
        User sanjigi = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        Answer answer1 = new Answer(javajigi, question, "Answers Contents1");
        Answer answer2 = new Answer(sanjigi, question, "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        assertThatThrownBy(() -> {
            answers.deleteSelf(javajigi, deleteDate);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
