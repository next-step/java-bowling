package bowling.frame.domain;

import bowling.frame.dto.IndexDTO;

public class IndexAssembler {

    private IndexAssembler() {
    }

    public static IndexDTO assemble(Index index) {
        return new IndexDTO(index.getIndex());
    }

}
