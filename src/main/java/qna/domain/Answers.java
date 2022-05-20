package qna.domain;

import java.util.List;
import java.util.Optional;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * 다른 회원이 쓴 답변이 있다면 true, 그렇지 않다면 false를 리턴합니다.
     *
     * @param user 확인할 회원
     * @return 다른 회원이 쓴 답변이 있다면 true, 그렇지 않다면 false
     */
    public boolean hasWrittenByOthers(User user) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(user));
    }
}
