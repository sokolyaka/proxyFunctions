package com.github.sokolyaka.proxyFunctions.core.reflection.exceptions;

public class UncheckedIllegalAccessException extends RuntimeException {
    public UncheckedIllegalAccessException() {
        super();
    }

    public UncheckedIllegalAccessException(String s) {
        super(s);
    }

    public UncheckedIllegalAccessException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UncheckedIllegalAccessException(Throwable throwable) {
        super(throwable);
    }

    protected UncheckedIllegalAccessException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }

    public UncheckedIllegalAccessException(IllegalAccessException e) {
    }
}
