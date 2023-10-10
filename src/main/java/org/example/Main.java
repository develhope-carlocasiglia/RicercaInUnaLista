package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Product> warehouse = new ArrayList<>();

        Product p1 = new Product("Air Max", 159.99);
        Product p2 = new Product("Air Force", 78.50);
        Product p3 = new Product("Stan Smith", 50.00);
        Product p4 = new Product(" Gazzelle", 45.50);

        warehouse.add(p1);
        warehouse.add(p2);
        warehouse.add(p3);
        warehouse.add(p4);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Di che prodotto vuoi sapere il prezzo?");
            String searchString = scanner.nextLine();

            // SOLUZIONE SENZA STREAM

            Optional<Product> optionalProduct = Optional.empty();

            for (Product p : warehouse) {
                if (p.getName().contains(searchString)) {
                    // se troviamo un prodotto lo mettiamo dentro alla scatola
                    optionalProduct = Optional.of(p);
                }
            }

            if (optionalProduct.isEmpty()) {
                System.out.println("Prodotto non trovato");
            } else {
                System.out.println("Il prezzo di " + searchString + " è " + optionalProduct.get().getPrice());
            }


            // SOLUZIONE CON STREAM

            List<Product> listOfProducts =
                    warehouse // prendo la lista
                    .stream() // APRO uno stream: inizio a far scorrere gli elementi di questa lista (che sono Product)
                    .filter( // mano a mano che scorrono li faccio passare attraverso un filtro
                            // questo filtro ci fa qualcosa sopra, uno per uno. Che cosa?
                            p -> { // li prende e gli da un nome, e li infila dentro una funzione
                                return p.getName().toLowerCase().contains(searchString);
                            })
                            .toList();

            System.out.println("Prodotti trovati: " + listOfProducts);
        }

    }
}

/*

FUNZIONI LAMBDA


Una funzione è qualcosa che prende qualcosa in ingresso, fa qualcosa, e restituisce qualcosa in uscita:

public String doSomething(Product p) {
    fa qualcosa
    return "Il nome del prodotto è " + p.getName();
}

Questa sopra è una funzione che ha un nome e che per forza appartiene ad una classe -> si chiama METODO

Possiamo anche creare funzioni senza un nome e che non appartengono ad una classe (lambda).
Le creiamo per usarle al volo, praticamente si tratta di codice impacchettato e usato al volo

p -> {
    return p.getName().equals("Nike Air Max"))
}

*/