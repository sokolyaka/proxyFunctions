package com.github.sokolyaka.proxyFunctions.core.function;

public class FunctionCall implements IWrappedFunction {
    @Override
    public void accept(IFunctionToInvoke f) {
        f.invoke();
    }
}
