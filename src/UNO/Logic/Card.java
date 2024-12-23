package Logic;

public class Card {
    private final String color;
    public Card(String color) {
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }
    public boolean isPlayable(Card lastPlayedCard) {
        return lastPlayedCard.getColor().equals(this.color) || this.color.equals("BLACK");
    }
}


