package task;



import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

    private static final String GREETING_MESSAGE =
            "Program Person - wersja konsolowa\n" +
                    "Autor: Paweł Rogaliński\n" +
                    "Data:  październik 2018 r.\n";

    private static final String MENU =
            "    M E N U   G Ł Ó W N E  \n" +
                    "1 - Podaj dane nowej osoby \n" +
                    "2 - Usuń dane osoby        \n" +
                    "3 - Modyfikuj dane osoby   \n" +
                    "4 - Wczytaj dane z pliku   \n" +
                    "5 - Zapisz dane do pliku   \n" +
                    "0 - Zakończ program        \n";
    private static final String MENU2 =
            "    M E N U   G Ł Ó W N E  \n" +
                    "WYBIERZ RODZAJ KOLEKCJI:\n"+
                    "1 - ArrayList\n" +
                    "2 - LinkedList\n" +
                    "3 - HashMap\n" +
                    "4 - TreeMap\n" +
                    "5 - HashSet\n" +
                    "6 - TreeSet\n"+
                    "7 - Porówanie obiektów z różnych kolekcji\n"+
                    "0 - Wyjście\n";

    private static final String CHANGE_MENU =
            "   Co zmienić?     \n" +
                    "1 - Imię           \n" +
                    "2 - Nazwisko       \n" +
                    "3 - Rok urodzenia  \n" +
                    "4 - Stanowisko     \n" +
                    "0 - Powrót do menu głównego\n";


    /**
     * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
     * prostych metod do realizacji dialogu z użytkownikiem
     * w oknie konsoli tekstowej.
     */
    private static final ConsoleUserDialog UI = new ConsoleUserDialog();


    public static void main(String[] args) {
        // Utworzenie obiektu aplikacji konsolowej
        // oraz uruchomienie głównej pętli aplikacji.
        PersonConsoleApp application = new PersonConsoleApp();
        application.runMainLoop2();
    }


    /*
     *  Referencja do obiektu, który zawiera dane aktualnej osoby.
     */
    private Person currentPerson = null;


    /*
     *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
     *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
     *         w której program się zatrzymuje aż do zakończenia
     *         działania za pomocą metody System.exit(0);
     */
    public void runMainLoop() {
        UI.printMessage(GREETING_MESSAGE);

        while (true) {
            UI.clearConsole();
            showCurrentPerson();

            try {
                switch (UI.enterInt(MENU + "==>> ")) {
                    case 1:
                        // utworzenie nowej osoby
                        currentPerson = createNewPerson();
                        break;
                    case 2:
                        // usunięcie danych aktualnej osoby.
                        currentPerson = null;
                        UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
                        break;
                    case 3:
                        // zmiana danych dla aktualnej osoby
                        if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
                        changePersonData(currentPerson);
                        break;
                    case 4: {
                        // odczyt danych z pliku tekstowego.
                        String file_name = UI.enterString("Podaj nazwę pliku: ");
                        //currentPerson = Person.readFromFile(file_name);
                        UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
                    }
                    break;
                    case 5: {
                        // zapis danych aktualnej osoby do pliku tekstowego
                        String file_name = UI.enterString("Podaj nazwę pliku: ");
                        //Person.printToFile(file_name, currentPerson);
                        UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
                    }

                    break;
                    case 0:
                        // zakończenie działania programu
                        UI.printInfoMessage("\nProgram zakończył działanie!");
                        System.exit(0);
                } // koniec instrukcji switch
            } catch (PersonException e) {
                // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
                // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
                // poszczególnych atrybutów.
                // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
                UI.printErrorMessage(e.getMessage());
            }
        } // koniec pętli while
    }

    /**
     * Główna pętla programu, odpowiada za menu główne służące do wyboru kolekjci na której,
     * przedstawione będą wszelakie operacje. W zależności od wyboru użytkownika, operacje będzie
     * mógł przeprowadzać na 6 różnych kolekcjach, a do każdej z poprzednio odwiedzonych kolekcji,
     * będzie mogł wrócić oraz na nowo ją modyfikować.
     */
    public void runMainLoop2(){
        CollectionsEngine engine = new CollectionsEngine();
        while(true){
            try {
                switch (UI.enterInt(MENU2 + "Wpisz:")) {
                    case 1: //Wybor 1 - operacje na ArrayList
                        engine.arrayListMenu(UI, 1);
                        break;
                    case 2: //Wybor 2 - operacje na LinkedList
                        engine.arrayListMenu(UI, 2);
                        break;
                    case 3: //Wybor 3 - operacje na HashMap
                        engine.arrayListMenu(UI, 3);
                        break;
                    case 4: //Wybor 4 - operacje na TreeMap
                        engine.arrayListMenu(UI, 4);
                        break;
                    case 5: //Wybor 5 - operacje na HashSet
                        engine.arrayListMenu(UI, 5);
                        break;
                    case 6: //Wybor 6 - operacje na TreeSet
                        engine.arrayListMenu(UI, 6);
                        break;
                    case 7: //Wybor 7 - Przykladowe porownania identycznych-
                        //obiektow znajdujących się w róznych kolekcjach-
                        //z przesłonięciem metody equals()/hashCode() oraz bez
                        comparison();
                        break;
                    case 0: //Wyjście
                        UI.printMessage("\nKoniec działania programu!");
                        System.exit(0);
                        break;
                }
            }catch (PersonException e){
                System.out.println("Błąd w programie!");
            }
        }
    }

    /*
     * Metoda comparison odpowiedzialna jest za przykładowe pokazanie
     * porównania dwóch identycznych obiektów znajdujących się w różnych kolekcjach.
     * Poprzez standardowy operator porówniania (==/equals()), program nie jest w stanie
     * wykryć tych samych obiektów, zlokalizowanych jednak w innych typach kolekcji.
     * Przez to musimy przesłonić zarówno metodę equals() oraz hashCode() klasy Person,
     * aby pogram był w stanie wykryć te same instancje.
     * Poniższa metoda krótko prezentuje ten zabieg.
     */
    private void comparison() throws PersonException {
        List<Person> comList1 = new ArrayList<Person>();
        Set<Person> comList2 = new TreeSet<Person>();
        Map<String,Person> comlist3 = new HashMap<String, Person>();
        comlist3.put("Key",new Person("Uczen","Uczen"));
        comList1.add(new Person("Uczen","Uczen"));
        comList2.add(new Person("Uczen","Uczen"));

        System.out.println("Przykładowe porównania dla tego samego obiektu znajdującego się w "+
                "kolekcji ArrayList(Osoba 1) oraz kolekcji TreeSet(Osoba 2): \n");
        Iterator<Person> iterator = comList2.iterator();
        Person p = iterator.next();
        System.out.println("Osoba 1:");
        PersonConsoleApp.showPerson(comList1.get(0));
        System.out.println("Osoba 2:");
        PersonConsoleApp.showPerson(p);
        System.out.println("Standardowe porównanie - bez przesłonięcia metody equals():\n");
        System.out.println("Osoba 1 == Osoba 2: "+(comList1.get(0)==p));
        System.out.println("\nPorownanie z przesłoniętą metodą equals():\n");
        System.out.println("Osoba 1 == Osoba 2: "+(comList1.get(0).equals(p)));
        UI.enterString("Wciśnij ENTER: ");
        System.out.println("\n\n");
        System.out.println("\nPrzykładowe porównania dla tego samego obiektu znajdującego się w "+
                "kolekcji TreeSet(Osoba1) oraz HashMap(Osoba2): ");
        Person p2 = comlist3.get("Key");
        System.out.println("\nOsoba 1: ");
        PersonConsoleApp.showPerson(p);
        System.out.println("Osoba 2: ");
        PersonConsoleApp.showPerson(p2);
        System.out.println("Standardowe porównanie - bez przesłonięcia metody hashCode():\n");
        System.out.println("Osoba 1 == Osoba 2: "+(p==p2));
        System.out.println("\nPorownanie z przesłoniętą metodą hashCode():\n");
        System.out.println("Osoba 1 == Osoba 2: "+(p.hashCode()==p2.hashCode()));
        UI.enterString("\nNaciśnij ENTER:");
        System.out.println("\n\n");
    }

    /*
     *  Metoda wyświetla w oknie konsoli dane aktualnej osoby
     *  pamiętanej w zmiennej currentPerson.
     */
    void showCurrentPerson() {
        showPerson(currentPerson);
    }


    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy Person
     */
    static void showPerson(Person person) {
        StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("      Imię: ").append(person.getFirstName()).append("\n")
                    .append("  Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
                    .append("Stanowisko: ").append(person.getJob()).append("\n");
        } else
            sb.append( "Brak danych osoby\n" );
        UI.printMessage( sb.toString() );
    }


    /*
     * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
     * klasy Person i wypełnia atrybuty wczytanymi danymi.
     * Walidacja poprawności danych odbywa się w konstruktorze i setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    static Person createNewPerson(){
        String first_name = UI.enterString("Podaj imię: ");
        String last_name = UI.enterString("Podaj nazwisko: ");
        String birth_year = UI.enterString("Podaj rok ur.: ");
        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
        String job_name = UI.enterString("Podaj stanowisko: ");
        Person person;
        try {
            // Utworzenie nowego obiektu klasy Person oraz
            // ustawienie wartości wszystkich atrybutów.
            person = new Person(first_name, last_name);
            person.setBirthYear(birth_year);
            person.setJob(job_name);
        } catch (PersonException e) {
            // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
            // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
            // poszczególnych atrybutów.
            // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
            UI.printErrorMessage(e.getMessage());
            return null;
        }
        return person;
    }


    /*
     * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów
     * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
     * Walidacja poprawności wczytanych danych odbywa się w setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    static void changePersonData(Person person)
    {
        while (true) {
            UI.clearConsole();
            showPerson(person);

            try {
                switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
                    case 1:
                        person.setFirstName(UI.enterString("Podaj imię: "));
                        break;
                    case 2:
                        person.setLastName(UI.enterString("Podaj nazwisko: "));
                        break;
                    case 3:
                        person.setBirthYear(UI.enterString("Podaj rok ur.: "));
                        break;
                    case 4:
                        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
                        person.setJob(UI.enterString("Podaj stanowisko: "));
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
            } catch (PersonException e) {
                // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
                // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
                // poszczególnych atrybutów.
                // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
                UI.printErrorMessage(e.getMessage());
            }
        }
    }


}  // koniec klasy PersonConsoleApp