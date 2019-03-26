package com.github.sokolyaka.proxyFunctions.core.function;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockFunction implements IWrappedFunction {
    private final IWrappedFunction origin;
    private final Lock lock;

    public TryLockFunction(IWrappedFunction origin) {
        this(origin, new ReentrantLock());
    }

    public TryLockFunction(IWrappedFunction origin, Lock lock) {
        this.origin = origin;
        this.lock = lock;
    }

    @Override
    public void accept(IFunctionToInvoke f) {
        if (lock.tryLock()) {
            try {
                origin.accept(f);
            } finally {
                lock.unlock();
            }
        }
    }
}
