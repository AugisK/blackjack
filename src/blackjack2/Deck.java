
package blackjack2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Deck {
    private ArrayList<Card> kortos = new ArrayList();

    
    public void createDeck(){
        
        for(Suit suitKortos: Suit.values()){
            for(Value valueKortos: Value.values()){
                kortos.add(new Card(valueKortos, suitKortos));
            }
        }
    }
    
    public void shuffleDeck(){
        Collections.shuffle(kortos);
    }
   
    public void printDeck(){
      
        for(Card korta: kortos){
            
            System.out.println(korta.printCard());
        }
        
        System.out.println("");
    }
    
    public void removeCard(int i){
        kortos.remove(i);
    }
    
    public Card getCard(int i){
        return kortos.get(i);
    }
    
    public void addCard(Card pridetiKorta){
        kortos.add(pridetiKorta);
    }
    
    public void deal(Deck pagrindinisDeck){
        kortos.add(pagrindinisDeck.getCard(0));
        pagrindinisDeck.removeCard(0);
    }
    
    public int getCardsValue(){
        int total=0;
        int tuzai=0;
        
        for(Card korta: kortos){
            switch(korta.getValue()){
                case DU: total+=2; break;
                case TRYS: total+=3; break;
                case KETURI: total+=4; break;
                case PENKI: total+=5; break;
                case SESI: total+=6; break;
                case SEPTYNI: total+=7; break;
                case ASTUONI: total+=8; break;
                case DEVYNI: total+=9; break;
                case DESIMT: total+=10; break;
                case BARTUKAS: total+=10; break;
                case DAMA: total+=10; break;
                case KARALIUS: total+=10; break;
                case TUZAS: tuzai+=1; break;
            }
        }
        
        for(int i=0; i<tuzai; i++){
            
            if(total>10) total += 1;
            else total += 11;
        }
        return total;
    }
    
    public void toDeck(Deck pagrindinisDeck){
        
        int deckSize = kortos.size();
        
        for(int i=0; i<deckSize; i++){
            pagrindinisDeck.addCard(getCard(i));
        }
        
        for(int i=0; i<deckSize; i++){
            removeCard(0);
        }
    }
    
    public int DeckSize(){
        return kortos.size();
    }
    
}

