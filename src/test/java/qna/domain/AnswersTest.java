package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    private List<Answer> tempAnswers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tempAnswers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        tempAnswers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
    }

    @Test
    @DisplayName("생성 테스트")
    void createTest() {

        Answers answers = Answers.of(tempAnswers);
        assertThat(answers.getAnswers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("한번에 모든 질문 삭제 하는 기능")
    void deleteAllTest() {
        Answers answers = Answers.of(tempAnswers);

        answers.getAnswers().
                forEach(answer -> assertThat(answer.isDeleted()).isFalse());

        answers.deleteAll();

        answers.getAnswers().
                forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

}
