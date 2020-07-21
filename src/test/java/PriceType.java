public enum PriceType {
    Дороже("Дороже", 2),
    Дешевле("Дешевле", 1);

    public String type;
    public int id;

    PriceType(String type, int id) {
        this.type = type;
        this.id = id;
    }
}