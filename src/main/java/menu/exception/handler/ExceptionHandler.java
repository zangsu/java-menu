package menu.exception.handler;

import java.util.function.Supplier;

public interface ExceptionHandler {
    <T> T get(Supplier<T> supplier);

    void run(Runnable runnable);
}
