package christmas.Model;

public enum Menu {

    MUSHROOM_SOUP(6000, 0, "양송이수프"),
    TAPAS(5500, 0, "타파스"),
    CAESAR_SALAD(8000, 0, "시저샐러드"),
    T_BONE_STEAK(55000, 0, "티본스테이크"),
    BBQ_RIBS(54000, 0, "바비큐립"),
    SEAFOOD_PASTA(35000, 0, "해산물파스타"),
    CHRISTMAS_PASTA(25000, 0, "크리스마스파스타"),
    CHOCOLATE_CAKE(15000, 0, "초코케이크"),
    ICE_CREAM(5000, 0, "아이스크림"),
    ZERO_COLA(3000, 0, "제로콜라"),
    RED_WINE(60000, 0, "레드와인"),
    CHAMPAGNE(25000, 0, "샴페인"),
    NONE(0, 0, "없음");

    private final Integer price;
    private Integer count;
    private String name;

    private Menu(Integer price, Integer count, String name) {
        this.price = price;
        this.count = count;
        this.name = name;
    }

    public static Menu getMenu(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return NONE;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Integer getCount() {
        return this.count;
    }

    public String getName() {
        return this.name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static Integer countLumpSumBeforeDiscount() {
        Integer lumpSumBeforeDiscount = 0;
        for (Menu menu : Menu.values()) {
            int count = menu.getCount();
            if (count != 0) {
                lumpSumBeforeDiscount = lumpSumBeforeDiscount + menu.getPrice() * menu.getCount();
            }
        }
        return lumpSumBeforeDiscount;
    }


}
