import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: akirkpatrick
 * Date: 06/04/12
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public class Runner {
    private static final int NUM_CARDS = 9;
    private long ITERATIONS = 0;
    private int SOLUTIONS = 0;

    public static Card[] cards = {
            new Card(new Creature[]{
                    new Octopus(Creature.HEAD),
                    new RedFish(Creature.HEAD),
                    new Seahorse(Creature.HEAD),
                    new OrangeFish(Creature.TAIL)
            }),
            new Card(new Creature[]{
                    new RedFish(Creature.TAIL),
                    new RedFish(Creature.HEAD),
                    new Octopus(Creature.HEAD),
                    new OrangeFish(Creature.TAIL)
            }),
            new Card(new Creature[]{
                    new RedFish(Creature.TAIL),
                    new Seahorse(Creature.HEAD),
                    new OrangeFish(Creature.HEAD),
                    new OrangeFish(Creature.TAIL)
            }),
            new Card(new Creature[]{
                    new RedFish(Creature.HEAD),
                    new Octopus(Creature.HEAD),
                    new Octopus(Creature.TAIL),
                    new Seahorse(Creature.TAIL)
            }),
            new Card(new Creature[]{
                    new OrangeFish(Creature.HEAD),
                    new Octopus(Creature.HEAD),
                    new OrangeFish(Creature.HEAD),
                    new RedFish(Creature.HEAD)
            }),
            new Card(new Creature[]{
                    new Octopus(Creature.TAIL),
                    new OrangeFish(Creature.TAIL),
                    new RedFish(Creature.TAIL),
                    new OrangeFish(Creature.HEAD)
            }),
            new Card(new Creature[]{
                    new Seahorse(Creature.HEAD),
                    new RedFish(Creature.HEAD),
                    new Octopus(Creature.HEAD),
                    new Seahorse(Creature.HEAD)
            }),
            new Card(new Creature[]{
                    new Seahorse(Creature.TAIL),
                    new Octopus(Creature.TAIL),
                    new Seahorse(Creature.HEAD),
                    new RedFish(Creature.TAIL)
            }),
            new Card(new Creature[]{
                    new Octopus(Creature.TAIL),
                    new OrangeFish(Creature.TAIL),
                    new Seahorse(Creature.TAIL),
                    new Seahorse(Creature.TAIL)
            })
    };

    public static void main(String[] args) {
        HashSet<Card> remainingCards = new HashSet<Card>();
        remainingCards.addAll(Arrays.asList(cards));
        long ticks = System.currentTimeMillis();
        Runner runner = new Runner();
        runner.attempt(new Stack<PlacedCard>(), remainingCards);
        System.out.println("Number of possibilities: " + runner.ITERATIONS);
        System.out.println("Solutions: " + runner.SOLUTIONS);
        System.out.println("Time: " + (System.currentTimeMillis() - ticks) + "ms");
        System.out.println("\n\nPress ENTER to exit");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private void attempt(Stack<PlacedCard> layout, HashSet<Card> remainingCards) {
//        System.out.println("Attempting solution: "+layout.size());
        for (Card card : remainingCards) {
            for (int orientation = 0; orientation < 4; orientation++) {
                PlacedCard newCard = new PlacedCard(card, orientation);
                ITERATIONS++;
                if (test(layout, newCard)) {
                    layout.push(newCard);
                    if (layout.size() == NUM_CARDS) {
                        SOLUTIONS++;
//                        System.out.println("FOUND A SOLUTION!!! IN " + ITERATIONS + " TESTS!");
//                        for (PlacedCard pc : layout) {
//                            System.out.println("Card: " + pc.card.num + ", Orientation: " + pc.orientation);
//                        }
                    } else {
                        HashSet<Card> rest = new HashSet<Card>(remainingCards);
                        rest.remove(card);
                        attempt(layout, rest);
                    }
                    layout.pop();
                }
            }
        }
    }

    private boolean test(Stack<PlacedCard> layout, PlacedCard newCard) {
        for (PlacedCard pc : layout) {
            if (pc.card.equals(newCard.card)) {
                return false;
            }
        }

        int position = layout.size();

        int col = (position) % 3;
        int row = (position) / 3;

        if (col > 0) {
            PlacedCard toLeft = layout.get(position - 1);
            if (!toLeft.testRight(newCard)) {
                return false;
            }
        }
        if (row > 0) {
            PlacedCard above = layout.get(position - 3);
            if (!above.testBelow(newCard)) {
                return false;
            }
        }
        return true;
    }

}
