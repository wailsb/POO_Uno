package Logic;

public class NonActionCard extends Card {
    private final int value;
    public NonActionCard(int value,String color) {
        super(color);
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
    @Override
    public boolean isPlayable(Card lastPlayedCard) {
        if (lastPlayedCard instanceof NonActionCard) {
            return ((NonActionCard) lastPlayedCard).getValue() == this.value;
        }
        return super.isPlayable(lastPlayedCard);
    }
}
