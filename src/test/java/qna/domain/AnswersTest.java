package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {


    private Answers answers;

    @BeforeEach
    void init() {
        Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
        Answer A2 = new Answer(UserTest.SANJIGI, Q2, "Answers Contents2");
        answers = new Answers(Arrays.asList(A1,A2));
    }

    @DisplayName(" 질문자와 답변글의 모든 답변자가 같은 경우 삭제를 할 수 없다.")
    @Test
    void testCase1() {
        User user = UserTest.JAVAJIGI;
        Answers answers = createAnswers(UserTest.JAVAJIGI, UserTest.SANJIGI);
        Answers deletedAnswers = answers.delete(user);

        Assertions.assertThat(deletedAnswers.deletedAnswersSize()).isEqualTo(2);
    }



    Answers createAnswers(User user, User user2) {
        Question Q1 = new Question("title1", "contents1").writeBy(user);
        Answer A1 = new Answer(user, Q1, "Answers Contents1");
        Answer A2 = new Answer(user2, Q1, "Answers Contents2");
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);
        return new Answers(Arrays.asList(A1, A2));

    }
}