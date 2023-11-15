package christmas.Model;

public enum SpecialDate {
    SPECIAL_THREE(3),
    SPECIAL_TEN(10),
    SPECIAL_SEVENTEEN(17),
    SPECIAL_TWENTY_FOUR(24),
    SPECIAL_TWENTY_FIVE(25),
    SPECIAL_THIRTY_ONE(31);
    private final Integer date;

    SpecialDate(Integer date) {
        this.date = date;
    }

    public Integer getDate() {
        return date;
    }
}
