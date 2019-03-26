package com.github.sokolyaka.proxyFunctions.core.reflection.exceptions;

public class UncheckedInvocationTargetException extends RuntimeException {
    public UncheckedInvocationTargetException() {
        super();
    }

    public UncheckedInvocationTargetException(String s) {
        super(s);
    }

    public UncheckedInvocationTargetException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UncheckedInvocationTargetException(Throwable throwable) {
        super(throwable);
    }

    protected UncheckedInvocationTargetException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
