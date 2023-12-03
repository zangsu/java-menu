package menu;

import menu.exception.handler.RetryHandler;

public class Application {
    public static void main(String[] args) {
        MenuController controller = new MenuController(new RetryHandler());
        controller.run();
    }
}
