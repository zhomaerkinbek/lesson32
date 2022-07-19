public enum Coin {
    FIVE(5),
    TEN(10),
    TWENTY_FIVE(25);
    private int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
