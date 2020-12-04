package step3.domain;

import step3.state.Symbol;

@FunctionalInterface
public interface SymbolSupply {
    Symbol create(int value);
}
