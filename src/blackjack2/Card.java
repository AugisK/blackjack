
package blackjack2;

public class Card {
    private Value value;
    private Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }
    
    public String printCard(){
        return suit.toString() + " - " + value.toString(); 
    }
    
    public Value getValue(){
        return value;
    }
}
