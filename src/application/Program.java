package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.BrazilTaxService;
import entities.RentalService;
import model.entities.CarRental;
import model.entities.Vehicle;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner entrada = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental data");
		System.out.println("Car model: ");
		String model = entrada.nextLine();
		System.out.println("Pickup (dd/MM/yyyy hh:mm): ");
		Date start = sdf.parse(entrada.nextLine());
		System.out.println("Return (dd/MM/yyyy hh:mm): ");
		Date finish = sdf.parse(entrada.nextLine());
		
		CarRental carRental = new CarRental (new Vehicle(model), start, finish);
		
		System.out.println("Enter price per hour:");
		Double pricePerHour = entrada.nextDouble();
		System.out.println("Enter price per day: ");
		Double pricePerDay = entrada.nextDouble();
		
		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rs.processInvoice(carRental);
		
		
		System.out.println("INVOICE: ");
		System.out.printf("Basic payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println();
		System.out.printf("Tax: " + String.format("%.2f",carRental.getInvoice().getTax()));
		System.out.println();
		System.out.printf("Total Payment: " + String.format("%.2f", carRental.getInvoice().totalPayment()));
	}

}

