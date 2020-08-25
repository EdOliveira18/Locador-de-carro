package entities;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay;
	private TaxService taxService;
	
	public RentalService() {
		
	}
	

	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}
	
	

	public Double getPricePerHour() {
		return pricePerHour;
	}


	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}


	public Double getPricePerDay() {
		return pricePerDay;
	}


	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


	public TaxService getTaxService() {
		return taxService;
	}


	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}


	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getFinish().getTime();
		long t2 = carRental.getStart().getTime();
		double i = (double)(t1 - t2)/1000/60/60;
		
		double basicPayment;
		if(i <= 12) {
			basicPayment = pricePerHour * Math.ceil(i);
		}
		else {
			basicPayment = pricePerDay * Math.ceil(i/24);
		}
		double tax = taxService.tax(basicPayment);
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}


