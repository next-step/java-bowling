package qna.domain;

import java.util.ArrayList;

public class AnswersTest {
    public static final Answers AS1 = new Answers(
            new ArrayList<Answer>() {{
                add(AnswerTest.A1);
            }}
    );
    public static final Answers AS2 = new Answers(
            new ArrayList<Answer>() {{
                add(AnswerTest.A2);
            }}
    );
}
