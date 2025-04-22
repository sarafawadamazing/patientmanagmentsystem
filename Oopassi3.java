
package oopassi3;// Interface for notifications
import java.util.*;

// Vital Sign class to store vital sign details.
class VitalSign {
    private double temperature;
    private int heartRate;
    private int bloodPressure;

    public VitalSign(double temperature, int heartRate, int bloodPressure) {
        this.temperature = temperature;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + "°C, Heart Rate: " + heartRate + " bpm, Blood Pressure: " + bloodPressure + " mmHg";
    }
}

// Interface for notifications
interface Notifiable {
    void sendNotification(String recipient, String message);
}

// EmailNotification class for sending email notifications
class EmailNotification implements Notifiable {
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending email to: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("Email sent successfully!\n");
    }
}

// SMSNotification class for sending SMS notifications
class SMSNotification implements Notifiable {
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending SMS to: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("SMS sent successfully!\n");
    }
}

// ReminderService class to send reminders
class ReminderService {
    private Notifiable notifier;

    public ReminderService(Notifiable notifier) {
        this.notifier = notifier;
    }

    public void sendReminder(String recipient, String message) {
        notifier.sendNotification(recipient, message);
    }
}

// EmergencyAlert class to trigger emergency alerts
class EmergencyAlert {
    private Notifiable notifier;

    public EmergencyAlert(Notifiable notifier) {
        this.notifier = notifier;
    }

    public void triggerAlert(String recipient, String message) {
        notifier.sendNotification(recipient, "EMERGENCY ALERT: " + message);
    }
}

// PanicButton class to press and trigger emergency alert
class PanicButton {
    private EmergencyAlert emergencyAlert;

    public PanicButton(EmergencyAlert emergencyAlert) {
        this.emergencyAlert = emergencyAlert;
    }

    public void press(String recipient) {
        emergencyAlert.triggerAlert(recipient, "Panic button pressed!");
    }
}

// ChatServer class to manage chat history
class ChatServer {
    private static List<String> chatHistory = new ArrayList<>();

    public void sendMessage(String from, String to, String message) {
        String chatEntry = from + " to " + to + ": " + message;
        chatHistory.add(chatEntry);
        System.out.println(chatEntry);
    }

    public void showChatHistory() {
        if (chatHistory.isEmpty()) {
            System.out.println("No chat history available.");
        } else {
            System.out.println("\n[Chat History]");
            for (String entry : chatHistory) {
                System.out.println(entry);
            }
        }
    }
}

// ChatClient class to send messages
class ChatClient {
    private String name;
    private ChatServer server;

    public ChatClient(String name, ChatServer server) {
        this.name = name;
        this.server = server;
    }

    public void sendMessage(String to, String message) {
        server.sendMessage(name, to, message);
    }
}

// VideoCall class to manage video calls
class VideoCall {
    public void startCall(String participant1, String participant2) {
        String link = "https://meet.example.com/" + UUID.randomUUID();
        System.out.println("Video call between " + participant1 + " and " + participant2);
        System.out.println("Join at: " + link);
    }
}

// AppointmentManager class to manage appointments
class AppointmentManager {
    private static ArrayList<String> appointments = new ArrayList<>();

    public static void addAppointment(String name, Scanner scanner) {
        System.out.print("Enter appointment details (date/time/with whom): ");
        String details = scanner.nextLine();
        String appointment = name + " - " + details;
        appointments.add(appointment);
        System.out.println("Appointment scheduled.");
    }

    public static void viewAppointments(String name) {
        System.out.println("\nAppointments for " + name + ":");
        boolean found = false;
        for (String appointment : appointments) {
            if (appointment.startsWith(name + " - ")) {
                System.out.println(appointment);
                found = true;
            }
        }
        if (!found) System.out.println("No appointments found.");
    }

    public static void viewAllAppointments() {
        System.out.println("\n[All Appointments]");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (String appt : appointments) {
                System.out.println(appt);
            }
        }
    }
}

// PatientMenu class to manage patient options
class PatientMenu {
    private Scanner scanner;
    private ChatServer server;
    private String name;
    private PanicButton panicButton;
    private static List<VitalSign> vitalSigns = new ArrayList<>();

    public PatientMenu(String name, ChatServer server, PanicButton panicButton, Scanner scanner) {
        this.name = name;
        this.server = server;
        this.panicButton = panicButton;
        this.scanner = scanner;
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("\n[Patient Menu]");
            System.out.println("[1] Upload Vital Signs");
            System.out.println("[2] View Doctor Feedback");
            System.out.println("[3] Schedule an Appointment");
            System.out.println("[4] View My Appointments");
            System.out.println("[5] Trigger Emergency Panic Button");
            System.out.println("[6] Send Message to Doctor");
            System.out.println("[7] View Chat History");
            System.out.println("[8] Start Video Call");
            System.out.println("[9] Exit Patient Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt(); scanner.nextLine();

            switch(option) {
                case 1:
                    uploadVitalSigns();
                    break;
                case 2:
                    System.out.println("No feedback available.");
                    break;
                case 3:
                    AppointmentManager.addAppointment(name, scanner);
                    break;
                case 4:
                    AppointmentManager.viewAppointments(name);
                    break;
                case 5:
                    System.out.print("Enter your doctor email: ");
                    String recipient = scanner.nextLine();
                    panicButton.press(recipient);
                    break;
                case 6:
                    System.out.print("Enter doctor's name: ");
                    String docName = scanner.nextLine();
                    System.out.print("Enter your message: ");
                    String msg = scanner.nextLine();
                    new ChatClient(name, server).sendMessage(docName, msg);
                    break;
                case 7:
                    server.showChatHistory();
                    break;
                case 8:
                    System.out.print("Enter doctor's name: ");
                    String doctor = scanner.nextLine();
                    new VideoCall().startCall(name, doctor);
                    break;
                case 9:
                    System.out.println("Exiting Patient Menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while(option != 9);
    }

    private void uploadVitalSigns() {
        System.out.print("Enter temperature (°C): ");
        double temperature = scanner.nextDouble();
        System.out.print("Enter heart rate (bpm): ");
        int heartRate = scanner.nextInt();
        System.out.print("Enter blood pressure (mmHg): ");
        int bloodPressure = scanner.nextInt();
        scanner.nextLine();  // consume the newline character

        VitalSign vitalSign = new VitalSign(temperature, heartRate, bloodPressure);
        vitalSigns.add(vitalSign);

        System.out.println("Vital signs uploaded successfully.");
    }

    public static List<VitalSign> getVitalSigns() {
        return vitalSigns;
    }
}

// DoctorMenu class to manage doctor options
class DoctorMenu {
    private Scanner scanner;
    private ChatServer server;
    private String name;

    public DoctorMenu(String name, ChatServer server, Scanner scanner) {
        this.name = name;
        this.server = server;
        this.scanner = scanner;
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("\n[Doctor Menu]");
            System.out.println("[1] View Patient Vitals");
            System.out.println("[2] Give Feedback");
            System.out.println("[3] View Appointments");
            System.out.println("[4] Send Message to Patient");
            System.out.println("[5] View Chat History");
            System.out.println("[6] Start Video Call");
            System.out.println("[7] Send Patient Reminder");
            System.out.println("[8] Exit Doctor Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt(); scanner.nextLine();

            switch(option) {
                case 1:
                    viewPatientVitals();
                    break;
                case 2:
                    System.out.println("Feedback sent to patient.");
                    break;
                case 3:
                    AppointmentManager.viewAppointments(name);
                    break;
                case 4:
                    System.out.print("Enter patient's name: ");
                    String patient = scanner.nextLine();
                    System.out.print("Enter your message: ");
                    String msg = scanner.nextLine();
                    new ChatClient(name, server).sendMessage(patient, msg);
                    break;
                case 5:
                    server.showChatHistory();
                    break;
                case 6:
                    System.out.print("Enter patient's name: ");
                    String patName = scanner.nextLine();
                    new VideoCall().startCall(name, patName);
                    break;
                case 7:
                    System.out.print("Enter patient email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter reminder message: ");
                    String reminder = scanner.nextLine();
                    new ReminderService(new EmailNotification()).sendReminder(email, reminder);
                    break;
                case 8:
                    System.out.println("Exiting Doctor Menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while(option != 8);
    }

    private void viewPatientVitals() {
        System.out.print("Enter patient's name to view vitals: ");
        String patientName = scanner.nextLine();

        // Filter vital signs for the specified patient (assuming you match by name for now)
        boolean found = false;
        for (VitalSign vs : PatientMenu.getVitalSigns()) {
            if (patientName.equals(name)) {
                System.out.println(vs);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vitals available for the patient.");
        }
    }
}

// Main class to start the system
public class Oopassi3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatServer server = new ChatServer();
        EmergencyAlert emergencyAlert = new EmergencyAlert(new EmailNotification());
        PanicButton panicButton = new PanicButton(emergencyAlert);
        int option;

        do {
            System.out.println("\n**** Remote Health Monitoring System ****");
            System.out.println("[1] Login as a Patient");
            System.out.println("[2] Login as a Doctor");
            System.out.println("[3] Login as an Administrator");
            System.out.println("[4] Manage Appointments");
            System.out.println("[5] Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt(); scanner.nextLine();

            switch(option) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();
                    new PatientMenu(patientName, server, panicButton, scanner).showMenu();
                    break;
                case 2:
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    new DoctorMenu(doctorName, server, scanner).showMenu();
                    break;
                case 3:
                    AppointmentManager.viewAllAppointments();
                    break;
                case 4:
                    AppointmentManager.viewAllAppointments();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while(option != 5);

        scanner.close();
    }
}








