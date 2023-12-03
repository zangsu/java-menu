package menu.domain.coach;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import menu.domain.menu.Menu;
import menu.exception.MenuException;

public class Coach {
    public static final int MAX_BANNED_MENU_NUM = 2;
    private final CoachName name;

    //todo 나중에 래핑
    private final List<Menu> banedMenus;
    private final List<Menu> selectedMenus = new ArrayList<>();

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

    public boolean selectMenu(Menu menu){
        if(selectedMenus.size() >= 5){
            throw MenuException.CANT_SELECT_MORE_MENU.makeException();
        }
        if(banedMenus.contains(menu)){
            return false;
        }
        if (selectedMenus.contains(menu)) {
            return false;
        }
        selectedMenus.add(menu);
        return true;
    }

    public String getName() {
        return name.getName();
    }

    public List<Menu> getSelectedMenus() {
        return Collections.unmodifiableList(selectedMenus);
    }
}
