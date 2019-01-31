
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GuestList {

    private ArrayList<Guest> guests = new ArrayList<>();

    public GuestList() {

    }

    public void readFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter relative path of file: ");
        FileReader readFile = new FileReader(in.readLine());
        BufferedReader inFile = new BufferedReader(readFile);
        System.out.println("\nReading File...\n");
        String input = inFile.readLine();
        while (input != null) {
            tokenizeString(input);
            input = inFile.readLine();
        }
        inFile.close();
    }

    private void tokenizeString(String input) {
        StringTokenizer st = new StringTokenizer(input);
        while (st.hasMoreTokens()) {
            guests.add(new Guest(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
        }
    }

    // Adds new guest in sorted order
    public void addGuest(Guest newGuest) {
        if (guests.indexOf(newGuest) == -1) {
            System.out.println("Guest already on List\n" + guests.get(guests.indexOf(newGuest)).toString());
            return;
        }
        int posIndex = 0;
        while (posIndex < guests.size() && newGuest.compareGuests(guests.get(posIndex + 1)) > 0) {
            posIndex++;
        }
        guests.add(posIndex, newGuest);
    }

    public void insertionSort() {
        for (int j = 1; j < guests.size(); j++) {
            Guest temp = guests.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareGuests(guests.get(possibleIndex - 1)) < 0) {
                guests.set(possibleIndex, guests.get(possibleIndex - 1));
                possibleIndex--;
            }
            guests.set(possibleIndex, temp);
        }
    }

    // Method to make a guest object with a given first and last name
    public static Guest enterGuestName() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Guest temp = new Guest("", "", "", "");
        System.out.print("Enter the last name of a guest: ");
        temp.setLastName(in.readLine());
        System.out.print("Enter the first name of a guest: ");
        temp.setFirstName(in.readLine());
        return temp;
    }

    // Command Methods
    // Find a guest with name entered
    public Guest binarySearchRunner(Guest target) {
        int index = binarySearch(target);
        if (index == -1) {
            return null;
        } else {
            return guests.get(index);
        }
    }

    public int binarySearch(Guest target) {
        int left = 0;
        int right = guests.size() - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (guests.get(middle).compareGuests(target) > 0) {
                right = middle - 1;
            } else if (guests.get(middle).compareGuests(target) < 0) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    // Get number of guest responses
    public void guestNumbers() {
        int attending = 0, notAttending = 0, maybe = 0;
        for (Guest i : guests) {
            if (i.getResponse().equals("yes")) {
                attending++;
            } else if (i.getResponse().equals("no")) {
                notAttending++;
            } else {
                maybe++;
            }
        }
        System.out.println("Number of guests attending: " + attending + "\nNumber of guests not attending: "
                + notAttending + "\nNumber of guests without response: " + maybe);
    }

    // Change a guest's response
    public void changeResponse(Guest temp) throws IOException {
        if (temp == null) {
            System.out.println("Guest not on list");
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Old Response: " + temp.getResponse() + "\nEnter new response: ");
            String response = in.readLine();
            if (response.equals(temp.getResponse())) {
                System.out.println("New response same as old, no changes made");
            } else {
                temp.setResponse(response);
                System.out.println("New response recorded");
            }
        }
    }

    // Find a guest's colleagues
    public void findColleagues(Guest temp) {
        if (temp == null) {
            System.out.println("Guest not on list");
        } else {
            System.out.println("Company: " + temp.getCompany());
            for (Guest i : guests) {
                if (i.getCompany().equals(temp.getCompany())) {
                    System.out.println(i.toString());
                }
            }
        }
    }

    // Print guest list
    public void printList() {
        for (Guest i : guests) {
            System.out.println("\n" + i.toString());
        }
    }

    // Command Chooser
    public void runStuff(char input) throws IOException {
        if (input == 'G') {
            System.out.println(binarySearchRunner(enterGuestName()).toString());
        } else if (input == 'L') {
            printList();
        } else if (input == 'N') {
            guestNumbers();
        } else if (input == 'A') {
            Guest temp = enterGuestName();
            changeResponse(temp);
            addGuest(temp);
        } else if (input == 'R') {
            changeResponse(enterGuestName());
        } else if (input == 'C') {
            findColleagues(enterGuestName());
        } else {
            System.out.println("Not a command, try again");
        }
    }

    public static void main(String[] args) throws IOException {
        GuestList list = new GuestList();
        list.readFile();
        list.insertionSort();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Commands: \nG: Guest Information\nL: List Guests\nN: Number of Guests\nA: Add a Guest\nR: Change a Response\nC: Display a Guest's Colleagues\nQ: Quit");
        System.out.print("Enter a Command: ");
        char input = in.readLine().toUpperCase().charAt(0);
        while (input != 'Q') {
            list.runStuff(input);
            System.out.print("Enter a Command: ");
            input = in.readLine().toUpperCase().charAt(0);
        }
        list.printList();
    }

}
