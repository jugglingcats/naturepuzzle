/**
 * Created by IntelliJ IDEA.
 * User: akirkpatrick
 * Date: 06/04/12
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class Creature {
    public static final String HEAD = "H";
    public static final String TAIL = "T";
    private String type;

    public Creature(String type) {
        this.type=type;
    }
    public boolean compatibleWith(Creature creature) {
        if ( !this.getClass().equals(creature.getClass()) )
            return false;

        return !creature.type.equals(this.type);
    }
}
