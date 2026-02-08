package by.megatop.enums;

public enum ClientCategory {

    MEN("Мужчины"),
    WOMEN("Женщины"),
    CHILDREN("Дети");

    private String categoryName;

    ClientCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }


}
