import java.util.Scanner;

// Exception for invalid train number
class InvalidTrainNumberException extends Exception {
    InvalidTrainNumberException(String message) {
        super(message);
    }
}

// Exception when required seats are not available
class UnavailableSeatsException extends Exception {
    UnavailableSeatsException(String message) {
        super(message);
    }
}

// Exception for invalid passenger details
class InvalidPassengerDetailsException extends Exception {
    InvalidPassengerDetailsException(String message) {
        super(message);
    }
}

// Exception for cancellation after permitted time
class CancellationTimeException extends Exception {
    CancellationTimeException(String message) {
        super(message);
    }
}

// Exception for invalid ticket number
class InvalidTicketNumberException extends Exception {
    InvalidTicketNumberException(String message) {
        super(message);
    }
}

// Railway reservation class
class RailwayReservation {
    int trainNumber = 101;
    String trainName = "Chennai Express";
    int totalSeats = 50;
    int availableSeats = 50;
    int ticketNumber = 1001;
    boolean ticketBooked = false;

    // Search for a train
    void searchTrain(int number) throws InvalidTrainNumberException {
        if (number != trainNumber) {
            throw new InvalidTrainNumberException("Invalid Train Number!");
        }

        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + trainName);
        System.out.println("Available Seats: " + availableSeats);
    }

    // Book a railway ticket
    void bookTicket(int number, String passengerName, int age, int seats)
            throws InvalidTrainNumberException,
            UnavailableSeatsException,
            InvalidPassengerDetailsException {

        // Check train number
        if (number != trainNumber) {
            throw new InvalidTrainNumberException("Invalid Train Number!");
        }

        // Check passenger details
        if (passengerName.length() == 0 || age <= 0) {
            throw new InvalidPassengerDetailsException("Invalid Passenger Details!");
        }

        // Check seat availability
        if (seats <= 0 || seats > availableSeats) {
            throw new UnavailableSeatsException("Seats are not available!");
        }

        // Update available seats
        availableSeats = availableSeats - seats;
        ticketBooked = true;

        System.out.println("Ticket Booked Successfully!");
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Seats Booked: " + seats);
    }

    // Cancel a booked ticket
    void cancelTicket(int number, boolean withinTime)
            throws InvalidTicketNumberException,
            CancellationTimeException {

        // Check ticket number
        if (!ticketBooked || number != ticketNumber) {
            throw new InvalidTicketNumberException("Invalid Ticket Number!");
        }

        // Check cancellation time
        if (!withinTime) {
            throw new CancellationTimeException("Cancellation time has expired!");
        }

        // Restore the seats
        availableSeats = totalSeats;
        ticketBooked = false;

        System.out.println("Ticket Cancelled Successfully!");
    }
}

// Main class
public class RailwayTicketReservationSystem {
    public static void main(String[] args) {
        Scanner osc = new Scanner(System.in);

        // Create railway reservation object
        RailwayReservation railway = new RailwayReservation();

        int choice;

        // Display menu until user chooses Exit
        do {
            System.out.println("\n===== RAILWAY RESERVATION SYSTEM =====");
            System.out.println("1. Search Train");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = osc.nextInt();
            osc.nextLine();

            try {
                switch (choice) {

                    // Search train
                    case 1: {
                        System.out.print("Enter Train Number: ");
                        int number = osc.nextInt();

                        railway.searchTrain(number);
                        break;
                    }

                    // Book ticket
                    case 2: {
                        System.out.print("Enter Train Number: ");
                        int number = osc.nextInt();
                        osc.nextLine();

                        System.out.print("Enter Passenger Name: ");
                        String passengerName = osc.nextLine();

                        System.out.print("Enter Passenger Age: ");
                        int age = osc.nextInt();

                        System.out.print("Enter Number of Seats: ");
                        int seats = osc.nextInt();

                        railway.bookTicket(number, passengerName, age, seats);
                        break;
                    }

                    // Cancel ticket
                    case 3: {
                        System.out.print("Enter Ticket Number: ");
                        int number = osc.nextInt();

                        System.out.print("Is cancellation within permitted time (true/false): ");
                        boolean withinTime = osc.nextBoolean();

                        railway.cancelTicket(number, withinTime);
                        break;
                    }

                    // Exit
                    case 4: {
                        System.out.println("Thank you!");
                        break;
                    }

                    // Invalid menu choice
                    default: {
                        System.out.println("Invalid Choice!");
                    }
                }
            }

            // Handle invalid train number
            catch (InvalidTrainNumberException e) {
                System.out.println(e.getMessage());
            }

            // Handle unavailable seats
            catch (UnavailableSeatsException e) {
                System.out.println(e.getMessage());
            }

            // Handle invalid passenger details
            catch (InvalidPassengerDetailsException e) {
                System.out.println(e.getMessage());
            }

            // Handle cancellation after permitted time
            catch (CancellationTimeException e) {
                System.out.println(e.getMessage());
            }

            // Handle invalid ticket number
            catch (InvalidTicketNumberException e) {
                System.out.println(e.getMessage());
            }

            // Executes after every transaction
            finally {
                System.out.println("Transaction Completed.");
            }

        } while (choice != 4);

        // Close Scanner
        osc.close();
    }
}