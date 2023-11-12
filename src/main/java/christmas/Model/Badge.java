package christmas.Model;

public enum Badge {

    STAR(-5000, "별"),
    TREE(-10000, "트리"),
    SANTA(-20000,  "산타"),
    NONE(0,"없음");

    private final Integer under;
    private String name;

    private Badge(Integer under, String name) {
        this.under = under;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public Integer getUnder(){ return this.under; }

}
