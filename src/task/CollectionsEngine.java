package task;

import java.util.*;
/**
 * Klasa odpowiedzialna za operacje na kolekcjach
 * autor: Patryk Uzarowski
 */

/**
 * Klasa ArrayListEngine odpowiedzialna jest za menu konkretnej kolekjci
 * oraz wszystkie możliwe operacje związanie z daną kolekcją.
 */
public class CollectionsEngine {
    private final Scanner scanner = new Scanner(System.in);

    private static final String SUBMENU =
            "    S U B  M E N U  \n" +
                    "OPERACJE NA DANEJ KOLEKCJI:\n"+
                    "1 - Dodawanie\n" +
                    "2 - Odejmowanie\n" +
                    "3 - Wypisanie (iterowanie)\n" +
                    "0 - Wyjście\n";
    //Wszystkie kolekcje, na których będą przeprowadzane operacje
    //opisane w SUBMENU. Kolekcje są polami niestatycznymi, aby użytkownik
    //w każdej chwili mógł powrócić do kolekcji, na której wcześniej pracował.

    private List<Person> list1 = new ArrayList<Person>();
    private List<Person> list2 = new LinkedList<Person>();
    private Map<String,Person> list3 = new HashMap<String, Person>();
    private Map<String,Person> list4 = new TreeMap<String, Person>();
    private Set<Person> list5 = new HashSet<Person>();
    private Set<Person> list6 = new TreeSet<Person>();;

    /*
    Pętla odpowiedzialna za SUBMENU programu, a więc menu operacji na kolekcjach.
    W zależności od wybranej opcji, użytkownik może przeprowadzić dodawanie, odejmowanie
    oraz wypisanie (Iterowanie) danej Kolekcji. Dodatkowym parametrami wejściowymi metody
    jest instancja ConsoleUserDialog oraz wartość całkowita int. Dzięki wartości całkowitej
    program będzie wiedział na jakim typie Kolekcji obecnie pracuje, a przez to użyte zostaną
    inne metody do poszczególnych operacji. Obiekt ConsoleUserDialog potrzebny jest do użycia metod
    wcześniej zaimplementowanych.
     */
    public int arrayListMenu(ConsoleUserDialog UI,int engineChoice) throws PersonException {
        while(true){
            System.out.println("\n\n");
            switch (UI.enterInt(SUBMENU + "Wpisz: ")){
                case 1: //Dodawanie
                    additionEngine(engineChoice);
                    break;
                case 2: //Odejmowanie
                    subtractionEngine(engineChoice);
                    break;
                case 3://Wypisanie
                    iterateEngine(engineChoice);
                    UI.enterString("Naciśnij Enter: ");
                    //System.out.println("Naciśnij ENTER: ");
                    //scanner.nextLine();
                    break;
                case 0: //Wyjscie;
                    UI.printMessage("\nWyjście z menu kolekcji!\n");
                    return 0;

            }
        }
    }

    /*
    Następne 3 metody to "Silniki" naszego programu, dzięki którym program będzie wiedział
    na jakich kolekcjach bazuje, a co za tym idzie, dostosuje odpowiednie metody do danego typu
    kolekcji oraz do wybranej przez użytkownika operacji.
     */
    private void additionEngine(int choice){
        switch(choice){
            case 1: listAddition(list1);
                break;
            case 2: listAddition(list2);
                break;
            case 3: mapAddition(list3);
                break;
            case 4: mapAddition(list4);
                break;
            case 5: setAddition(list5);
                break;
            case 6: setAddition(list6);
                break;
        }
    }

    private void subtractionEngine(int choice){
        switch(choice){
            case 1: listSubtraction(list1);
                break;
            case 2: listSubtraction(list2);
                break;
            case 3: mapSubtraction(list3);
                break;
            case 4: mapSubtraction(list4);
                break;
            case 5: setSubtraction(list5);
                break;
            case 6: setSubtraction(list6);
                break;
        }
    }
    private void iterateEngine(int choice){
        switch(choice){
            case 1: listIteration(list1);
                break;
            case 2: listIteration(list2);
                break;
            case 3: mapIterate(list3);
                break;
            case 4: mapIterate(list4);
                break;
            case 5: setIteration(list5);
                break;
            case 6: setIteration(list6);
                break;
        }
    }

    /*
     * Metoda listAddition dodaje nowy element do ArrayList/LinkedList
     */
    private void listAddition(List list){
        Person p = PersonConsoleApp.createNewPerson();
        if(p!=null) {
            list.add(p);
            System.out.println("\nOsoba została dodana do naszej listy!\n");
        }
    }
    /*
     * Metoda listSubtraction() usuwa element z ArrayListy/LinkedListy.
     * Użytkownik może wybrać, który element z kolekcji chce usunąć
     */
    private void listSubtraction(List list){
        if(list.isEmpty())
            System.out.println("\nKolekcja jest pusta!");
        else{
            listIteration(list);
            System.out.println("\nKtórą osobę chcesz usunąć?"+
                    "\n Wpisz numer 1-"+list.size()+ " odpowiadający pozycji osoby w kolekcji: ");
            try{
                int x = scanner.nextInt();
                if(x<1 || x>list.size())
                    System.out.println("\nBrak takiej osoby w kolekcji!");
                else{
                    list.remove(x-1);
                    System.out.println("\nObiekt "+x+" został usunięty z kolekcji");
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Brak takiej osoby w kolekcji!");
                scanner.nextLine();
            }
        }
    }
    /*
    Metoda odpowiedzialna za wypisanie wszystkich elementów ArrayListy/LinkedListy
     */
    private void listIteration(List<Person> list) {
        System.out.println("\nWypisanie obecnych obiektów w kolekcji:");
        int i = 1;
        for (Person person : list) {
            System.out.println("\n    Osoba " + i + " : ");
            PersonConsoleApp.showPerson(person);
            i++;
        }
    }
    /*
    Metoda odpowiedzialna za umożliwienie użytkownikowi dodawanie do HashMapy/TreeMapy
    Użytkownik sam podaje unikatowy klucz, którym zapisze osobe do danej Mapy.
     */
    private void mapAddition(Map<String, Person> list){
        Person p = PersonConsoleApp.createNewPerson();
        if(p!=null){
            System.out.println("Podaj unikatowy klucz dla danej osoby: ");
            String password = scanner.nextLine();
            list.put(password,p);
            System.out.println("\n Osoba zostala dodana do Mapy!");
        }
    }
    private void mapSubtraction(Map<String,Person> list) {
        int i = 1;
        String chosen;
        if (list.isEmpty())
            System.out.println("\nBrak osob w mapie!");
        else {
            Set<Map.Entry<String, Person>> entrySet = list.entrySet();
            Iterator<Map.Entry<String, Person>> iterator = entrySet.iterator();
            System.out.println("Wybierz, która osobe chcesz usunac:\n" +
                    "Klucze przypisane do osob w mapie:");
            for (String key : list.keySet()) {
                System.out.println("Osoba " + i + ": " + key);
                i++;
            }
            System.out.println("\nWybierz:");
            chosen = scanner.nextLine();
            if (list.containsKey(chosen)) {
                list.remove(chosen);
                System.out.println("\nOsoba została usunięta z mapy!");
            } else
                System.out.println("Brak takiego klucza w mapie!");
        }
    }
    /*
    Metoda odpowiedzialna za wypisywanie kolejnych elementów danej mapy
     */
    private void mapIterate(Map<String,Person> list){
        System.out.println("\nWypisanie obecnych obiektów w kolekcji:");
        int i = 1;
        for(Person person : list.values()) {
            System.out.println("\n    Osoba "+i+" : ");
            PersonConsoleApp.showPerson(person);
            i++;
        }
    }
    /*
    Metoda odpowiedzialna za dodanie utworzonej przez użytkownika osoby do HashSetu/TreeSetu
     */
    private void setAddition(Set list){
        Person p = PersonConsoleApp.createNewPerson();
        if(p!=null) {
            list.add(p);
            System.out.println("\nOsoba została dodana do naszej listy!\n");
        }
    }
    /*
    Metoda odpowiedzialna za usunięcie wybranej przez użytkownika osoby z
    HashSetu/TreeSetu
     */
    private void setSubtraction(Set list){
        if(!list.isEmpty()) {
            System.out.println("Wybierz, która osobe chcesz usunac:\n ");
            setIteration(list);
            System.out.println("Wpisz numer 1-" + list.size() + ": ");
            try {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= list.size()) {
                    Iterator<Person> iterator = list.iterator();
                    for (int i = 1; i <= choice; i++) {
                        iterator.hasNext();
                        iterator.next();
                    }
                    iterator.remove();
                } else{
                    System.out.println("\nBrak takiej osoby w kolekcji!");
                }

            }catch(InputMismatchException e){
                System.out.println("\nBrak takiej osoby w kolekcji!");
                scanner.nextLine();
            }
        }else
            System.out.println("\nKolekcja jest pusta!");
    }
    /*
    Metoda wypisująca zawartość danej Hashlisty/TreeListy
     */
    private void setIteration(Set list){
        Iterator<Person> iterator = list.iterator();
        int i=1;
        while(iterator.hasNext()){
            System.out.println("\n    Osoba "+i+" : ");
            PersonConsoleApp.showPerson(iterator.next());
            i++;
        }
    }
}