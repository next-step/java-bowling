package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    private User user;
    private Question question;
    private Answer answer;
    private Answers answers;

    @BeforeEach
    void setUp() {
        user = new User(1L, "javajigi", "aa", "aa", "aa");
        question = new Question("title1", "contents1").writeBy(user);
        answer = new Answer(user, QuestionTest.Q1, "Answers Contents1");
        answers = new Answers(Arrays.asList(answer));
    }

    @DisplayName("삭제 히스토리 일급 객체를 생성할 수 있다.")
    @Test
    void create() {
        DeleteHistories deleteHistories = DeleteHistories.of(question, answers);

        final List<DeleteHistory> actual = deleteHistories.getDeleteHistories();

        assertThat(actual).hasSize(2);
    }
}