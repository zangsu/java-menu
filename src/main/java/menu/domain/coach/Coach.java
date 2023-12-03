package menu.domain.coach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.menu.Menu;
import menu.exception.MenuException;

public class Coach {
    public static final int MAX_BANNED_MENU_NUM = 2;
    public static final int WEEKDAY_COUNT = 5;
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
        if(selectedMenus.size() >= WEEKDAY_COUNT){
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

    public List<String> getSelectedMenuNames() {
        return selectedMenus.stream()
                .map(Menu::getName)
                .collect(Collectors.toList());
    }
}
