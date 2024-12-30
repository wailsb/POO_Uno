package Logic;

public  class Card {
    public enum Colors {
        RED,GREEN,BLACK,YELLOW,BLUE
    }
    private final Colors color;
    public Card(Colors color) {
        this.color = color;
    }
    public Colors getColor() {
        return this.color;
    }
    public  boolean isPlayable(Card lastPlayedCard) {
        return lastPlayedCard.getColor() == this.color || this.color == Colors.BLACK;
    }
}

