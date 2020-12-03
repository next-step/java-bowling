package step2.domain;

import step2.state.Symbol;

@FunctionalInterface
public interface SymbolSupply {
    Symbol create(int value);
}
