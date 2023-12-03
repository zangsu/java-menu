package menu.domain.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import menu.exception.MenuException;

public enum Category {
    JAPANESE(1, "일식",
            List.of(Menu.GYUDON, Menu.UDON, Menu.MISOSHIRU, Menu.SUSHI, Menu.KATSUDON, Menu.ONIGIRI, Menu.HAYARICE,
                    Menu.RAMEN, Menu.OKONOMIYAKI)),
    KOREAN(2, "한식",
            List.of(Menu.GIMBAP, Menu.KIMCHI_JJIGAE, Menu.SSAMBAP, Menu.DOJANG_JJIGAE, Menu.BIBIMBAP, Menu.KALGUKSU,
                    Menu.BULGOGI, Menu.TTEOKBOKKI, Menu.JEYUK_BOKKEUM)),
    CHINESE(3, "중식",
            List.of(Menu.KKANPUNGGI, Menu.BOKKEUM_MYUN, Menu.DONGPO_PORK, Menu.JAJANGMYEON, Menu.JJAMPPONG,
                    Menu.MAPO_TOFU, Menu.TANGSUYUK, Menu.TOMATO_EGG_STIR_FRY, Menu.GOCHUJANG_JAPCHAE)),
    ASIAN(4, "아시안",
            List.of(Menu.PAD_THAI, Menu.KHAO_PAD, Menu.NASI_GORENG, Menu.PINEAPPLE_FRIED_RICE, Menu.PHO,
                    Menu.TOM_YUM_KUNG, Menu.BANH_MI, Menu.VIETNAMESE_FRESH_SPRING_ROLLS, Menu.BUN_CHA)),
    WESTERN(5, "양식",
            List.of(Menu.LASAGNA, Menu.GRATIN, Menu.YAKISOBA, Menu.QUICHE, Menu.FRENCH_TOAST, Menu.BAGUETTE,
                    Menu.SPAGHETTI, Menu.PIZZA, Menu.PANINI));

    private final String name;
    private final List<Menu> menuList;
    private final int selectedNumber;

    Category(int selectedNumber, String name, List<Menu> menuList) {
        this.selectedNumber = selectedNumber;
        this.name = name;
        this.menuList = menuList;
    }

    public static Category from(int selectedNumber) {
        return Arrays.stream(values())
                .filter(category -> category.selectedNumber == selectedNumber)
                .findFirst()
                .orElseThrow(MenuException.INVALID_CATEGORY_NUMBER::makeException);
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menuList);
    }

    public String getName() {
        return name;
    }
}
