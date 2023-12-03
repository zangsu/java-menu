package menu;

import menu.exception.handler.ExceptionHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private final ExceptionHandler handler;

    public MenuController(ExceptionHandler handler) {
        this.handler = handler;
    }

    public void run(){
        outputView.printStartMessage();
        //코치 이름 입력
        //코치별 못먹는 메뉴 입력
        //추천 메뉴 출력
        //종료
    }
}
