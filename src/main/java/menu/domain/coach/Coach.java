package menu.domain.coach;

import java.util.ArrayList;
import java.util.List;
import menu.domain.menu.Menu;
import menu.exception.MenuException;

public class Coach {
    public static final int MAX_BANNED_MENU_NUM = 2;
    //todo 나중에 이름 래핑
    private final CoachName name;
    private final List<Menu> banedMenus;

    public Coach(String name) {
        this.name = new CoachName(name);
        this.banedMenus = new ArrayList<>();
    }

    public void addBanedMenu(List<Menu> menues){
        validateBanedMenu(menues);
        banedMenus.addAll(menues);
    }

    private void validateBanedMenu(List<Menu> menues) {
        validateDuplicatedMenu(menues);
        validateBannedMenuSize(menues);
    }

    private void validateBannedMenuSize(List<Menu> menues) {
        if(menues.size() > MAX_BANNED_MENU_NUM){
            throw MenuException.MAX_BANNED_MENU.makeException();
        }
    }

    private void validateDuplicatedMenu(List<Menu> menu) {
        int distinctCount = (int) menu.stream()
                .distinct()
                .count();
        if(distinctCount != menu.size()){
            throw MenuException.DUPLICATE_BANNED_MENU.makeException();
        }
    }

    public String getName() {
        return name.getName();
    }
}
