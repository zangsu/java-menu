package menu.domain.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import menu.exception.MenuException;

public enum Menu {
    GYUDON("규동"),
    UDON("우동"),
    MISOSHIRU("미소시루"),
    SUSHI("스시"),
    KATSUDON("가츠동"),
    ONIGIRI("오니기리"),
    HAYARICE("하이라이스"),
    RAMEN("라멘"),
    OKONOMIYAKI("오코노미야끼"),

    GIMBAP("김밥"),
    KIMCHI_JJIGAE("김치찌개"),
    SSAMBAP("쌈밥"),
    DOJANG_JJIGAE("된장찌개"),
    BIBIMBAP("비빔밥"),
    KALGUKSU("칼국수"),
    BULGOGI("불고기"),
    TTEOKBOKKI("떡볶이"),
    JEYUK_BOKKEUM("제육볶음"),

    KKANPUNGGI("깐풍기"),
    BOKKEUM_MYUN("볶음면"),
    DONGPO_PORK("동파육"),
    JAJANGMYEON("짜장면"),
    JJAMPPONG("짬뽕"),
    MAPO_TOFU("마파두부"),
    TANGSUYUK("탕수육"),
    TOMATO_EGG_STIR_FRY("토마토 달걀볶음"),
    GOCHUJANG_JAPCHAE("고추잡채"),

    PAD_THAI("팟타이"),
    KHAO_PAD("카오 팟"),
    NASI_GORENG("나시고렝"),
    PINEAPPLE_FRIED_RICE("파인애플 볶음밥"),
    PHO("쌀국수"),
    TOM_YUM_KUNG("똠얌꿍"),
    BANH_MI("반미"),
    VIETNAMESE_FRESH_SPRING_ROLLS("월남쌈"),
    BUN_CHA("분짜"),

    LASAGNA("라자냐"),
    GRATIN("그라탱"),
    YAKISOBA("뇨끼"),
    QUICHE("끼슈"),
    FRENCH_TOAST("프렌치 토스트"),
    BAGUETTE("바게트"),
    SPAGHETTI("스파게티"),
    PIZZA("피자"),
    PANINI("파니니");

    private static final Map<String, Menu> cachedMenus = new HashMap<>();
    static{
        for (Menu menu : Menu.values()) {
            cachedMenus.put(menu.name, menu);
        }
    }
    private final String name;

    Menu(String name) {
        this.name = name;
    }

    public static Menu from(String name){
        return Optional.ofNullable(cachedMenus.get(name))
                .orElseThrow(MenuException.NO_SUCH_MENU::makeException);
    }

}
