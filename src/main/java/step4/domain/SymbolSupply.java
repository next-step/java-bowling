package step4.domain;

import step4.state.Symbol;

@FunctionalInterface
public interface SymbolSupply {
    Symbol create(int value);
}
