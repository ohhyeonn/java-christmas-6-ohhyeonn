package christmas.View;

import christmas.Model.Menu;

public class OutputView {
    private static final String INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ORDERED_MENU = "<주문 메뉴>";
    private static final String DECEMBER = "12월 ";
    private static final String EVENT_INTRO = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String BLANC = " ";
    private static final String COUNT = "개";
    public static void printIntro() {
        System.out.println(INTRO);
    }


    public static void printMenu() {
        System.out.println();
        System.out.println(ORDERED_MENU);
        for(Menu menu : Menu.values()){
            int count = menu.getCount();
            if(count != 0){
                System.out.print(menu.getName()+BLANC);
                System.out.println(count+COUNT);
            }

        }

    }

    public static void printEventIntro(Integer date) {
        System.out.println(DECEMBER+date+EVENT_INTRO );
    }
}
