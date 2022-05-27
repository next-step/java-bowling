package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    private Answers answers;
    private Question question;
    private Answer answer;
    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    public void setUp() throws Exception {
        answers = new Answers();
        question = new Question(1L, "title1", "contents1", UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, question, "Answers Contents1");
        deleteHistories = new ArrayList<>();
    }

    @Test
    void add_normal_성공() {
        answers.add(answer);
        Assertions.assertThat(answers.answers()).contains(answer);
    }

    @Test
    void delete_normal_성공() throws Exception {
        assertThat(answer.isDeleted()).isFalse();
        answers.add(answer);
        answers.delete(UserTest.JAVAJIGI, deleteHistories);
        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }
}
