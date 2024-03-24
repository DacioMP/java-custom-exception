package model.entities;

import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Reservation {
	
	private Integer roomNumber;
	private LocalDate checkin;
	private LocalDate checkout;
	
	public static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Reservation() {}

	public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) throws DomainException {
		
		if (!checkout.isAfter(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}
	
	public long duration() {
		
		Duration diff = Duration.between(checkin.atStartOfDay(), checkout.atStartOfDay());
		
		return diff.toDays();
	}
	
	public void updateDates(LocalDate checkin, LocalDate checkout) throws DomainException {
		
		LocalDate now = LocalDate.now();
		
		if (checkin.isBefore(now) || checkout.isBefore(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if (!checkout.isAfter(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Reservation: Room " + roomNumber + ", ");
		sb.append("check-in: " + checkin.format(fmt) + ", ");
		sb.append("check-out: " + checkout.format(fmt) + ", ");
		sb.append(duration() + " nights");
		
		return sb.toString();
	}
	
}
