package com.seok2.bowling.frame.domain;

import com.seok2.bowling.frame.dto.IndexDTO;

public class IndexAssembler {

    private IndexAssembler() {
    }

    public static IndexDTO assemble(Index index) {
        return new IndexDTO(index.getIndex());
    }

}
