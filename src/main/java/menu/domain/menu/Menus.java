package menu.domain.menu;

import java.util.List;
import java.util.stream.Collectors;
import menu.exception.MenuException;

public class Menus {
    private final List<Menu> menus;

    public Menus(List<Menu> menus) {
        validateMenus(menus);
        this.menus = menus;
    }

    private void validateMenus(List<Menu> menus) {
        validateDuplicatedMenu(menus);
    }

    private void validateDuplicatedMenu(List<Menu> menu) {
        int distinctCount = (int) menu.stream()
                .distinct()
                .count();
        if (distinctCount != menu.size()) {
            throw MenuException.DUPLICATE_MENU.makeException();
        }
    }

    public boolean contains(Menu menu) {
        return menus.contains(menu);
    }

    public void add(Menu menu) {
        menus.add(menu);
    }

    public int size() {
        return menus.size();
    }

    public List<String> getMenusName() {
        return menus.stream()
                .map(Menu::getName)
                .collect(Collectors.toList());
    }
}
