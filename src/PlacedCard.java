/**
 * Created by IntelliJ IDEA.
 * User: akirkpatrick
 * Date: 06/04/12
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class PlacedCard {
    Card card;
    int orientation;

    public PlacedCard(Card card, int orientation) {
        this.card=card;
        this.orientation=orientation;
    }

    public boolean testRight(PlacedCard newCard) {
        int eastCreature=(5 - this.orientation) % 4;
        int westCreature=(3 - newCard.orientation);

        return card.creatures[eastCreature].compatibleWith(newCard.card.creatures[westCreature]);


    }

    public boolean testBelow(PlacedCard newCard) {
        int topCreature=(4 - newCard.orientation) % 4;
        int bottomCreature=(6 - this.orientation) % 4;

        return card.creatures[bottomCreature].compatibleWith(newCard.card.creatures[topCreature]);
    }
}
