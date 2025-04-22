# patientmanagmentsystem
i have created a patient management system for my oop assignment
Remote Health Monitoring System
Overview:
This project is a Remote Health Monitoring System that allows communication between doctors and patients, as well as health monitoring functionalities. It includes features like appointment scheduling, emergency alerts, sending messages, and video calls between doctors and patients.

----How to Run the Project---
Prerequisites:
Java 8 or higher

Apache NetBeans (IDE)

-----Steps to Run------
Open Apache NetBeans IDE.

1.Run the Project

In NetBeans, right-click on the project name and select Run or press Shift + F6 to run the project.

2.Interacting with the System

Upon running the project, a menu will appear with options to log in as either a Patient, Doctor, or Administrator.

3.Each role has different functionalities

.Patients can schedule appointments, send messages, and trigger emergency alerts.

.Doctors can view patient vitals, send feedback, and communicate with patients.

.Administrators can manage users, send email notifications, and view system logs.

4.Example Interaction

As a Patient, you can log in and upload vital signs, view appointments, and send a message to a doctor.

As a Doctor, you can view appointments, give feedback to patients, and send messages back to them.

5.Email Notifications (Simulated)

The system simulates sending email notifications. When a patient presses the panic button or a doctor sends a reminder, an email-like output will be displayed in the console.

6.Code Structure
Main Class: Assignment3.java

Menu Classes: AdminMenu.java, PatientMenu.java, DoctorMenu.java

Notification Classes: EmailNotification.java, SMSNotification.java, ReminderService.java

Emergency Classes: EmergencyAlert.java, PanicButton.java

Chat Classes: ChatServer.java, ChatClient.java

Video Call: VideoCall.java

Appointment Manager: AppointmentManager.java 
