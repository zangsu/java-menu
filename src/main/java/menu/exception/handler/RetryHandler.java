package menu.exception.handler;

import java.util.function.Supplier;
import menu.view.OutputView;

public class RetryHandler implements ExceptionHandler {
    private final OutputView outputView = new OutputView();

    @Override
    public <T> T get(Supplier<T> supplier) {
        return getOrRetry(supplier);
    }

    private <T> T getOrRetry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            } finally {
                outputView.newLine();
            }
        }
    }

    @Override
    public void run(Runnable runnable) {
        runOrRetry(runnable);
    }

    private void runOrRetry(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            } finally {
                outputView.newLine();
            }
        }
    }
}
