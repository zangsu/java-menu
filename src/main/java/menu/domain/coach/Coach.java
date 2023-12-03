package menu.domain.coach;

import java.util.List;
import java.util.stream.Collectors;
import menu.domain.menu.Menu;
import menu.domain.menu.Menus;
import menu.exception.MenuException;

public class Coach {
    public static final int MAX_BANNED_MENU_NUM = 2;
    public static final int WEEKDAY_COUNT = 5;
    private final CoachName name;
    private Menus banedMenus;
    private Menus selectedMenus;

    public Coach(String name) {
        this.name = new CoachName(name);
    }

    public void banMenus(List<Menu> menus){
        validateBannedMenuSize(menus);
        if(banedMenus != null){
            throw MenuException.ALREADY_BANNED_MENU.makeException();
        }
        banedMenus = new Menus(menus);
    }

    private void validateBannedMenuSize(List<Menu> menus) {
        if(menus.size() > MAX_BANNED_MENU_NUM){
            throw MenuException.MAX_BANNED_MENU.makeException();
        }
    }

    public boolean selectMenu(Menu menu){
        if(selectedMenus == null){
            selectedMenus = new Menus(List.of());
        }
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
        return selectedMenus.getMenusName();
    }
}
