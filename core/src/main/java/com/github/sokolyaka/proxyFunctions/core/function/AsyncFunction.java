package com.github.sokolyaka.proxyFunctions.core.function;

import java.util.concurrent.ExecutorService;

public class AsyncFunction implements IWrappedFunction {
    private final IWrappedFunction origin;
    private final ExecutorService service;

    public AsyncFunction(IWrappedFunction origin, ExecutorService service) {
        this.origin = origin;
        this.service = service;
    }

    @Override
    public void accept(final IFunctionToInvoke f) {
        service.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        origin.accept(f);
                    }
                });
    }
}
