package com.github.sokolyaka.proxyFunctions.core.function;

public class SafeFunction implements IWrappedFunction {
    private final IWrappedFunction origin;
    private final Logger logger;

    public SafeFunction(IWrappedFunction origin, Logger logger) {
        this.origin = origin;
        this.logger = logger;
    }

    @Override
    public void accept(IFunctionToInvoke f) {
        try {
            origin.accept(f);
        } catch (Throwable t) {
            logger.logError(t);
        }
    }

    public interface Logger {
        void logError(Throwable t);
    }
}
