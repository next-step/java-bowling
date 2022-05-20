package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    private Answers createAnswers(User user) {
        return new Answers(List.of(
                new Answer(user, new Question(), "contents")
        ));
    }

    @Nested
    class hasWrittenByOthers_메서드는 {
        private User me;
        private User answerUser;

        @Nested
        class 다른사람이_쓴_답변이_주어질경우 {

            @BeforeEach
            void setUp() {
                me = UserTest.JAVAJIGI;
                answerUser = UserTest.SANJIGI;
            }

            @Test
            void true를_리턴한다() {
                Answers answers = createAnswers(answerUser);

                boolean actual = answers.hasWrittenByOthers(me);

                assertThat(actual).isTrue();
            }

        }

        @Nested
        class 내가쓴_답변이_주어질경우 {

            @BeforeEach
            void setUp() {
                me = UserTest.JAVAJIGI;
                answerUser = UserTest.JAVAJIGI;
            }

            @Test
            void false를_리턴한다() {
                Answers answers = createAnswers(answerUser);

                boolean actual = answers.hasWrittenByOthers(me);

                assertThat(actual).isFalse();
            }

        }
    }
}
