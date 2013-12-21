/**
 * Created by IntelliJ IDEA.
 * User: akirkpatrick
 * Date: 06/04/12
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public class Card {
    Creature[] creatures;
    int num;

    public Card(int i, Creature[] creatures) {
        this.num=i;
        this.creatures=creatures;
    }
}
