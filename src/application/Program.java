package application;

import model.entities.Reservation;

import java.util.Locale;
import java.util.Scanner;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();
		
		sc.nextLine();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		LocalDate checkin = LocalDate.parse(sc.nextLine(), fmt);
		
		System.out.print("Check-out date (dd/MM/yyyy): ");
		LocalDate checkout = LocalDate.parse(sc.nextLine(), fmt);
		
		if (!checkout.isAfter(checkin)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		} else {
			Reservation reservation = new Reservation(roomNumber, checkin, checkout);
			System.out.println(reservation);
			
			System.out.println("");
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkin = LocalDate.parse(sc.nextLine(), fmt);
			
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkout = LocalDate.parse(sc.nextLine(), fmt);
			
			LocalDate now = LocalDate.now();
			
			if (checkin.isBefore(now) || checkout.isBefore(now)) {
				System.out.println("Error in reservation: Reservation dates for update must be future dates");
			} else if (!checkout.isAfter(checkin)) {
				System.out.println("Error in reservation: Check-out date must be after check-in date");
			} else {
				reservation.updateDates(checkin, checkout);
				System.out.println(reservation);
			}
		}
		
		sc.close();

	}

}
