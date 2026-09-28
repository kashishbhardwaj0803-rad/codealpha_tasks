import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    double pricePerNight;
    boolean isAvailable;

    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }
}

class Booking {
    String guestName;
    Room room;
    int nights;
    double totalAmount;

    public Booking(String guestName, Room room, int nights) {
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.totalAmount = room.pricePerNight * nights;
        room.isAvailable = false;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        // Adding Sample Rooms
        rooms.add(new Room(101, "Standard", 1500.0));
        rooms.add(new Room(102, "Standard", 1500.0));
        rooms.add(new Room(201, "Deluxe", 3000.0));
        rooms.add(new Room(202, "Deluxe", 3000.0));
        rooms.add(new Room(301, "Suite", 6000.0));

        System.out.println("=== Hotel Reservation System Initialized ===");

        while (true) {
            System.out.println("\n1. Search Available Rooms\n2. Make a Reservation\n3. View All Bookings\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            if (choice == 4) {
                System.out.println("Thank you for using Hotel Reservation System!");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Available Rooms ---");
                    for (Room r : rooms) {
                        if (r.isAvailable) {
                            System.out.printf("Room: %d | Category: %-10s | Price/Night: ₹%.2f\n", r.roomNumber, r.category, r.pricePerNight);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter Guest Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Room Number to book: ");
                    int roomNum = scanner.nextInt();
                    System.out.print("Enter Number of Nights: ");
                    int nights = scanner.nextInt();

                    Room selectedRoom = null;
                    for (Room r : rooms) {
                        if (r.roomNumber == roomNum && r.isAvailable) {
                            selectedRoom = r;
                            break;
                        }
                    }

                    if (selectedRoom != null) {
                        Booking newBooking = new Booking(name, selectedRoom, nights);
                        bookings.add(newBooking);
                        System.out.printf("✅ Booking Successful! Total Amount to pay: ₹%.2f\n", newBooking.totalAmount);
                    } else {
                        System.out.println("❌ Error: Room is either invalid or already booked!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Current Reservations ---");
                    if (bookings.isEmpty()) {
                        System.out.println("No active bookings found.");
                        break;
                    }
                    for (Booking b : bookings) {
                        System.out.printf("Guest: %-15s | Room: %d (%s) | Nights: %d | Total: ₹%.2f\n", 
                                b.guestName, b.room.roomNumber, b.room.category, b.nights, b.totalAmount);
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        scanner.close();
    }
}