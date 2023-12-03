package menu.domain.menu;

import java.util.List;

public enum Category {
    JAPANESE("일식",
            List.of(Menu.GYUDON, Menu.UDON, Menu.MISOSHIRU, Menu.SUSHI, Menu.KATSUDON, Menu.ONIGIRI, Menu.HAYARICE, Menu.RAMEN, Menu.OKONOMIYAKI)),
    KOREAN("한식",
            List.of(Menu.GIMBAP, Menu.KIMCHI_JJIGAE, Menu.SSAMBAP, Menu.DOJANG_JJIGAE, Menu.BIBIMBAP, Menu.KALGUKSU, Menu.BULGOGI, Menu.TTEOKBOKKI, Menu.JEYUK_BOKKEUM)),
    CHINESE("중식",
            List.of(Menu.KKANPUNGGI, Menu.BOKKEUM_MYUN, Menu.DONGPO_PORK, Menu.JAJANGMYEON, Menu.JJAMPPONG, Menu.MAPO_TOFU, Menu.TANGSUYUK, Menu.TOMATO_EGG_STIR_FRY, Menu.GOCHUJANG_JAPCHAE)),
    ASIAN("아시안",
            List.of(Menu.PAD_THAI, Menu.KHAO_PAD, Menu.NASI_GORENG, Menu.PINEAPPLE_FRIED_RICE, Menu.PHO, Menu.TOM_YUM_KUNG, Menu.BANH_MI, Menu.VIETNAMESE_FRESH_SPRING_ROLLS, Menu.BUN_CHA)),
    WESTERN("양식",
            List.of(Menu.LASAGNA, Menu.GRATIN, Menu.YAKISOBA, Menu.QUICHE, Menu.FRENCH_TOAST, Menu.BAGUETTE, Menu.SPAGHETTI, Menu.PIZZA, Menu.PANINI));

    private final String name;
    private final List<Menu> menuList;

    Category(String name, List<Menu> menuList) {
        this.name = name;
        this.menuList = menuList;
    }
}
