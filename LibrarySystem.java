import java.util.*;

class Library {
    List<String> books = new ArrayList<>();
    Set<String> categories = new HashSet<>();
    Map<Integer, String> issuedBooks = new HashMap<>();
    Queue<String> waitingList = new LinkedList<>();

    void addBook(String book, String category) {
        books.add(book);
        categories.add(category);
        System.out.println("Book added: " + book);
    }

    void issueBook(int id, String book) {
        if (books.contains(book)) {
            issuedBooks.put(id, book);
            books.remove(book);
            System.out.println("Book issued: " + book);
        }
        else {
            System.out.println("Book not available!");
        }
    }

    void returnBook(int id) {
        if (issuedBooks.containsKey(id)) {
            String book = issuedBooks.remove(id);
            books.add(book);
            System.out.println("Book returned: " + book);

            if (!waitingList.isEmpty()) {
                String student = waitingList.poll();
                System.out.println("Book can now be issued to: " + student);
            }
        }
        else {
            System.out.println("Invalid Student ID!");
        }
    }

    void addToWaitingList(String student) {
        waitingList.offer(student);
        System.out.println(student + " added to waiting list.");
    }

    void displayBooks() {
        System.out.println("\nAvailable Books:");

        for (String book : books) {
            System.out.println(book);
        }
    }

    void displayCategories() {
        System.out.println("\nBook Categories:");

        for (String category : categories) {
            System.out.println(category);
        }
    }

    void displayIssuedBooks() {
        System.out.println("\nIssued Books:");

        for (Map.Entry<Integer, String> entry : issuedBooks.entrySet()) {
            System.out.println(
                "Student ID: " + entry.getKey() +
                ", Book: " + entry.getValue()
            );
        }
    }

    void displayWaitingList() {
        System.out.println("\nWaiting List:");

        for (String student : waitingList) {
            System.out.println(student);
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner lib = new Scanner(System.in);

        Library library = new Library();

        System.out.print("Enter number of books: ");
        int n = lib.nextInt();
        lib.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter book name: ");
            String book = lib.nextLine();

            System.out.print("Enter category: ");
            String category = lib.nextLine();

            library.addBook(book, category);
        }

        System.out.println("\nEnter student ID to issue book:");
        int id = lib.nextInt();
        lib.nextLine();

        System.out.print("Enter book name to issue: ");
        String book = lib.nextLine();

        library.issueBook(id, book);

        System.out.print("\nEnter student name for waiting list: ");
        String student = lib.nextLine();

        library.addToWaitingList(student);

        System.out.print("\nEnter student ID to return book: ");
        int returnId = lib.nextInt();

        library.returnBook(returnId);

        library.displayBooks();
        library.displayCategories();
        library.displayIssuedBooks();
        library.displayWaitingList();

        lib.close();
    }
}