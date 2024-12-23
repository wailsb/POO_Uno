package Logic;

public class ActionCard extends Card {
    public enum Actions {
        SKIP,REVERSE,DRAW_TWO,WILD,DRAW_FOUR_WILD;
        static boolean isCompatible(Actions action,Colors color) {
            if (color == Colors.BLACK) {
                return action == Actions.DRAW_FOUR_WILD || action == Actions.WILD;
            } else {
                return action == Actions.SKIP || action == Actions.REVERSE || action == Actions.DRAW_TWO;
            }
        }
    }
    private final Actions action;
    public ActionCard(Actions action,Colors color) {
        super(color);
        if (Actions.isCompatible(action,color)) {
            this.action = action;
        } else {
            throw new IllegalArgumentException("Please Enter A Compatible Action And Color");
        }
    }
    public Actions getAction() {
        return this.action;
    }
    @Override
    public boolean isPlayable(Card lastPlayedCard) {
        if (lastPlayedCard instanceof ActionCard) {
            return ((ActionCard) lastPlayedCard ).getAction() == this.action;
        }
        return super.isPlayable(lastPlayedCard);
    }
}

