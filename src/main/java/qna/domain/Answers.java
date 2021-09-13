package qna.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answerList;

    public Answers() {
    }

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<DeleteHistory> deleteHistories() {
        return answerList.stream()
                .map(a -> a.setDeleted(true))
                .map(DeleteHistory::answer)
                .collect(Collectors.toList());
    }
}
