package Logic;

public class ActionCard extends Card {
    private final String action;
    public ActionCard(String action,String color) {
        super(color);
        this.action = action;
    }
    public String getAction() {
        return this.action;
    }
    @Override
    public boolean isPlayable(Card lastPlayedCard) {
        if (lastPlayedCard instanceof ActionCard) {
            return ((ActionCard) lastPlayedCard ).getAction().equals(this.action);
        }
        return super.isPlayable(lastPlayedCard);
    }
}

