package org.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main implements BookingMapper {
    public static Scanner scanner = new Scanner(System.in);
    private final String MYBATIS_CONFIG_PATH = "mybatis-config.xml";
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    BookingMapper bookingMapper;

    public Main() throws IOException {
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(MYBATIS_CONFIG_PATH));
        this.session = sqlSessionFactory.openSession();
        this.bookingMapper = session.getMapper(BookingMapper.class);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        int option;

        do {
            main.printMainMenu();
            option = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character from the buffer

            switch (option) {
                case 1:
                    System.out.print("Enter the XML file path: ");
                    String xmlFilePath = scanner.nextLine();
                    main.loadXmlData(xmlFilePath);
                    break;
                case 2:
                    System.out.print("Enter the CSV file path: ");
                    String csvFilePath = scanner.nextLine();
                    main.saveDataToCsv(csvFilePath);

                    break;
                case 3:
                    main.deleteAllBookings();
                    break;
                case 4:
                    main.insertBooking(main.createNewBooking());
                    break;
                case 5:
                    System.out.print("Inserte el id de la reserva que desea borrar" + "\n");
                    main.deleteBookingById(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("Inserte el id de la reserva que desea actualizar:");
                    main.updateBooking(main.getBookingById(scanner.nextInt()));
                    scanner.nextLine();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }

        } while (option != 0);
    }


    private void printMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Load data from an XML file");
        System.out.println("2. Save data to a CSV file");
        System.out.println("3. Delete all bookings");
        System.out.println("4. Add a booking");
        System.out.println("5. Delete a booking");
        System.out.println("6. Modify a booking");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private Booking createNewBooking() {
        Booking newBooking = new Booking();
        newBooking.setLocation_number(this.getLastLocationNumber() + 1);
        System.out.println("Creando nueva reserva.......");
        System.out.println("__________________________________________");
        System.out.print("Inserta el id del cliente:" + "\n");
        newBooking.setClientId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Inserta el nombre de cliente:" + "\n");
        newBooking.setClient(scanner.nextLine());
        System.out.print("Inserta el nombre de la agencia:" + "\n");
        newBooking.setAgency(scanner.nextLine());
        System.out.print("Inserta el id de la agencia:" + "\n");
        newBooking.setId_agency(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Inserta el precio de la reserva:" + "\n");
        newBooking.setPrice(scanner.nextDouble());
        scanner.nextLine();
        System.out.print("Inserta el id de la habitación:" + "\n");
        newBooking.setId_type(scanner.nextLine());
        System.out.print("Inserta el tipo de habitación:" + "\n");
        newBooking.setRoom(scanner.nextLine());
        System.out.print("Inserta el nombre del hotel:" + "\n");
        newBooking.setHotel(scanner.nextLine());
        System.out.print("Inserta el id del hotel:" + "\n");
        newBooking.setId_hotel(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Inserta el número de noches:" + "\n");
        newBooking.setRoom_nights(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Insera fecha de check-in:" + "\n");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = scanner.nextLine();
        try {
            newBooking.setCheck_in(dateFormat.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("__________________________________________");
        return newBooking;
    }

    @Override
    public void insertBooking(Booking booking) {
        try {
            bookingMapper.insertBooking(booking);
            session.commit();
            System.out.println("Booking added successfully!");
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            System.out.println("Error adding booking. Rolling back changes.");
        }
    }

    @Override
    public void updateBooking(Booking booking) {
        try {
            System.out.println("Current Booking Details:");
            printBooking(booking);

            int option;
            do {
                printEditMenu();

                option = scanner.nextInt();
                scanner.nextLine();  // Clear the newline character from the buffer

                switch (option) {
                    case 1:
                        System.out.print("Enter the new Location Number: ");
                        int newLocationNumber = scanner.nextInt();
                        booking.setLocation_number(newLocationNumber);
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("Enter the new Client ID: ");
                        int newClientID = scanner.nextInt();
                        booking.setClientId(newClientID);
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("Enter the new Client: ");
                        String newClient = scanner.nextLine();
                        booking.setClient(newClient);
                        break;
                    case 4:
                        System.out.print("Enter the new Agency: ");
                        String newAgency = scanner.nextLine();
                        booking.setAgency(newAgency);
                        break;
                    case 5:
                        System.out.print("Enter the new Agency ID: ");
                        int newAgencyID = scanner.nextInt();
                        booking.setId_agency(newAgencyID);
                        scanner.nextLine();
                        break;
                    case 6:
                        System.out.print("Enter the new Price: ");
                        double newPrice = scanner.nextDouble();
                        booking.setPrice(newPrice);
                        scanner.nextLine();
                        break;
                    case 7:
                        System.out.print("Enter the new Type ID: ");
                        String newTypeID = scanner.nextLine();
                        booking.setId_type(newTypeID);
                        break;
                    case 8:
                        System.out.print("Enter the new Room: ");
                        String newRoom = scanner.nextLine();
                        booking.setRoom(newRoom);
                        break;
                    case 9:
                        System.out.print("Enter the new Hotel ID: ");
                        int newHotelID = scanner.nextInt();
                        booking.setId_hotel(newHotelID);
                        scanner.nextLine();
                        break;
                    case 10:
                        System.out.print("Enter the new Hotel: ");
                        String newHotel = scanner.nextLine();
                        booking.setHotel(newHotel);
                        break;
                    case 11:
                        System.out.print("Enter the new Check-In Date (yyyy/MM/dd): ");
                        String newCheckInDate = scanner.nextLine();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        try {
                            booking.setCheck_in(dateFormat.parse(newCheckInDate));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 12:
                        System.out.print("Enter the new Room Nights: ");
                        int newRoomNights = scanner.nextInt();
                        booking.setRoom_nights(newRoomNights);
                        scanner.nextLine();
                        break;
                    case 13:
                        break;  // Finish updating
                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                }
            } while (option != 13);

            // Commit the changes to the database
            bookingMapper.updateBooking(booking);
            session.commit();
            System.out.println("Booking updated successfully!");
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            System.out.println("Error updating booking. Rolling back changes.");
        }
    }


    @Override
    public void deleteBookingById(int id) {
        try {
            bookingMapper.deleteBookingById(id);
            session.commit();
            System.out.println("Booking deleted successfully!");
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            System.out.println("Error deleting booking. Rolling back changes.");
        }
    }

    @Override
    public void deleteAllBookings() {
        try {
            bookingMapper.deleteAllBookings();
            session.commit();
            System.out.println("All bookings deleted successfully!");
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            System.out.println("Error deleting all bookings. Rolling back changes.");
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        try {
            return bookingMapper.getAllBookings();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Booking getBookingById(int bookingIdToModify) {
        try {
            return bookingMapper.getBookingById(bookingIdToModify);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getLastLocationNumber() {
        return bookingMapper.getLastLocationNumber();
    }

    @Override
    public void loadXmlData(String xmlFilePath) {
        try {
            bookingMapper.loadXmlData(xmlFilePath);
            session.commit();
            System.out.println("XML data loaded successfully!");
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            System.out.println("Error loading XML data. Rolling back changes.");
        }

    }

    @Override
    public void saveDataToCsv(String csvFilePath) {
        try {
            bookingMapper.saveDataToCsv(csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printBooking(Booking booking) {
        System.out.println("Booking Details:");
        System.out.println("ID: " + booking.getId());
        System.out.println("Location Number: " + booking.getLocation_number());
        System.out.println("Client ID: " + booking.getClientId());
        System.out.println("Client: " + booking.getClient());
        System.out.println("Agency ID: " + booking.getId_agency());
        System.out.println("Agency: " + booking.getAgency());
        System.out.println("Price: " + booking.getPrice());
        System.out.println("Type ID: " + booking.getId_type());
        System.out.println("Room: " + booking.getRoom());
        System.out.println("Hotel ID: " + booking.getId_hotel());
        System.out.println("Hotel: " + booking.getHotel());
        System.out.println("Check-In Date: " + booking.getCheck_in());
        System.out.println("Room Nights: " + booking.getRoom_nights());
    }

    private void printEditMenu() {
        System.out.println("\nUpdate Options:");
        System.out.println("1. Update Location Number");
        System.out.println("2. Update Client ID");
        System.out.println("3. Update Client");
        System.out.println("4. Update Agency");
        System.out.println("5. Update Agency ID");
        System.out.println("6. Update Price");
        System.out.println("7. Update Type ID");
        System.out.println("8. Update Room");
        System.out.println("9. Update Hotel ID");
        System.out.println("10. Update Hotel");
        System.out.println("11. Update Check-In Date");
        System.out.println("12. Update Room Nights");
        System.out.println("13. Finish Updating");
        System.out.print("Select an option: ");
    }


}
