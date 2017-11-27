
package blackjack2;

import java.util.Scanner;


public class BlackJack2 {

    public static void main(String[] args) {
        
        double money = 100.00;
        
        System.out.println("Sveiki atvyje į BlackJack kortų žaidimą");
        
        //kuriama kaladė
        Deck pagrindinis = new Deck();
        pagrindinis.createDeck();
        pagrindinis.shuffleDeck();
       
        
        Deck zaidejoDeck = new Deck();
        Deck dealerioDeck = new Deck();
        
        
        Scanner sc = new Scanner(System.in);
        while(money > 0){
            
            boolean endRound=false;
            System.out.println("\nJus turite " + money + "$\nĮveskite pinigu suma kuria norite pastatyti.");
            double statymas = sc.nextDouble();
            
            while(statymas>money || statymas==0){
                
                if(statymas==0) System.out.println("Tu turi kažką pastatyti!!!");
                else if (statymas>money) System.out.println("Jus neturite tiek pinigų");
                
                System.out.println("\nJus turite " + money + "$\nĮveskite pinigu suma kuria norite pastatyti.");
                statymas = sc.nextDouble();
            }
            
            //duodamos dvi kortos dealeriui
            dealerioDeck.deal(pagrindinis);        
            dealerioDeck.deal(pagrindinis);
            dealerioDeck.deal(pagrindinis);
            
            //duodamos dvi kortos zaidejui
            zaidejoDeck.deal(pagrindinis);
            zaidejoDeck.deal(pagrindinis);
            zaidejoDeck.deal(pagrindinis);
            
            while(true){
                System.out.println("---------------------------------------------------------------------\n"
                        + "Dealerio kortos: ");
                dealerioDeck.printDeck();

                System.out.println("---------------------------------------------------------------------\n"
                        + "Jūsų kortos: ");
                zaidejoDeck.printDeck();

                System.out.println("---------------------------------------------------------------------\n"
                        + "Jus turite " + zaidejoDeck.getCardsValue() + " taškų");
                System.out.println("Dar kortą (1), baigti(2)");
                int pasi = sc.nextInt();
                
                while(pasi!=1 && pasi!=2){
                    
                    System.out.println("Blogas pasirinkimas");
                    System.out.println("Dar kortą (1), baigti(2)");
                    pasi = sc.nextInt();
                }
                
                if(pasi == 1){
                    
                    //zaidėjui duodama papildoma korta
                    zaidejoDeck.deal(pagrindinis);
                    System.out.println("\nTu ištraukei: "+zaidejoDeck.getCard(zaidejoDeck.DeckSize()-1).printCard());
                    
                    if(zaidejoDeck.getCardsValue()>21){
                        System.out.println("Peršokai 21, pralaimėjei!!!");
                        money-=statymas;
                        
                        //roundo pabaiga
                        endRound=true;
                        break;
                    }
                
                }
                else if(pasi == 2){
                    break;
                }
                
            }
            
            //tašku lyginimas
            if(dealerioDeck.getCardsValue() > zaidejoDeck.getCardsValue() && endRound==false){
                System.out.println("Tu pralaimėjei, Dealeris surinko "+dealerioDeck.getCardsValue()+", tu surinkai "+zaidejoDeck.getCardsValue());
                money-=statymas;
                endRound=true;
            }

            //dealeriui duodama papildoma korta jei jos reikia
            while(dealerioDeck.getCardsValue() < 17 && endRound==false){
                dealerioDeck.deal(pagrindinis);
                System.out.println("\nDealeris ištraukė: "+dealerioDeck.getCard(dealerioDeck.DeckSize()-1).printCard());

            }


            if(dealerioDeck.getCardsValue()>21 && endRound==false){
                System.out.println("Dealeris surinko daugiau nei 21, todėl tu laimėjei.");
                money+=statymas;
                endRound=true;
            }

            if(dealerioDeck.getCardsValue() < zaidejoDeck.getCardsValue() && endRound==false){
                System.out.println("Tu laimėjei. Dealeris surinko "+dealerioDeck.getCardsValue()+", tu surinkai "+zaidejoDeck.getCardsValue());
                money+=statymas;
                endRound=true;
            } else if(dealerioDeck.getCardsValue() == zaidejoDeck.getCardsValue() && endRound==false){
                System.out.println("Surinkote vienoda kiekį tašku.");
                endRound=true;
            } else if(endRound==false){
                System.out.println("Tu pralaimėjei. Dealeris surinko "+dealerioDeck.getCardsValue()+", tu surinkai "+zaidejoDeck.getCardsValue());
                money-=statymas;
                endRound=true;
            }

            //sudedamos visos kortos atgal į kaladę
            zaidejoDeck.toDeck(pagrindinis);
            dealerioDeck.toDeck(pagrindinis);

            System.out.println("\n----------------------------------------------------------------------");                
            System.out.println("Roundo pabaiga.");
            System.out.println("----------------------------------------------------------------------");
        }
        
        System.out.println("Žaidimo pabaiga, jus praradote visus pinigus.");
    }
    
}
