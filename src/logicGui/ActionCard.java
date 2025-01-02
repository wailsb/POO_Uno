package logicGui;

public class ActionCard extends Card {
    public enum Actions {
        Skip,Reverse,Draw_2,Wild,DRAW_4_Wild;
        static boolean isCompatible(Actions action, Colors color) {
            if (color == Colors.Black) {
                return action == Actions.DRAW_4_Wild || action == Actions.Wild;
            } else {
                return action == Actions.Skip || action == Actions.Reverse || action == Actions.Draw_2;
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
            // Allow playing if the actions match or the colors match
            return ((ActionCard) lastPlayedCard).getAction() == this.action || super.isPlayable(lastPlayedCard);
        }
        // Allow playing if the colors match
        return super.isPlayable(lastPlayedCard);
    }
}

