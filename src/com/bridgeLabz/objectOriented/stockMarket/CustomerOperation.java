package com.bridgeLabz.objectOriented.stockMarket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.bridgeLabz.objectOriented.stockMarket.model.Share;
import com.bridgeLabz.utility.Util;

/*
 * This class gives the customer to do actions like purchasing a share from the
 * market where the stocks were given by the companies, also operations like
 * sell a share and display all informations of the stocks present in the
 * customer List.

 */
public class CustomerOperation {

	public static ArrayList<Share> customerShareList;

	/*
	 * Constructor to initialize the arrayList
	 */
	public CustomerOperation() {
		customerShareList = new ArrayList<Share>();
	}

	/*
	 * Object of ComapanyOperation class to access functions of that class.
	 */
	private CompanyOperation companyOperation = new CompanyOperation();

	/*
	 * This function ask the Customer user to enter the share which he want to
	 * purchase also the quantity of share he want to buy. If the share of the
	 * particular company is listed in the share market then the customer can
	 * purchase that share. If not present in the market it just simply reply with
	 * error message if present then that share is added to the person's list.
	 */
	public void buyShare() {
		System.out.println("Enter Share symbol :");
		String inputSymbol = Util.scanner.next();
		Share foundShare = findShare(CompanyOperation.companyShareList, inputSymbol);
		if (foundShare == null) {
			System.out.println(inputSymbol + " share not found in found.");
		}

		int foundPosition = findShare(companyOperation.getCompanyShareList(), foundShare);
		if (foundPosition < 0) {
			System.out.println("company Share " + foundShare.getSymbol() + " was not found.");
			return;
		}
		System.out.println("Enter Quantity you want to buy :");
		int quantity = Util.scanner.nextInt();

		if (foundShare.getQuantity() < quantity) {
			System.out.println("Opps! availabe quantity : " + foundShare.getQuantity());
			return;
		}
		int updatedQuantity = foundShare.getQuantity() - quantity;
		String name = foundShare.getName();
		String symbol = foundShare.getSymbol();
		double price = foundShare.getPrice();
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		Share UpdatedShare = Share.createShare(name, symbol, price, updatedQuantity, date, time);
		CompanyOperation.companyShareList.set(foundPosition, UpdatedShare);
		Share customerPurchase = Share.createShare(name, symbol, price, quantity, date, time);
		if (customerShareList.add(customerPurchase)) {
			System.out.println("Thank you for purchasing " + customerPurchase.getSymbol() + " Share.");
		} else {
			System.out.println("Error purchasing share.");

		}

	}

	/*
	 * This function takes <Share> List and A share class type input parameter
	 * iterate through the loop If matches then it returns index position of the
	 * <Share> from the list else returns negative value.
	 
	 */
	private int findShare(ArrayList<Share> list, Share share) {
		for (int i = 0; i < list.size(); i++) {
			String shareSymbol = list.get(i).getSymbol();
			if (share.getSymbol().equals(shareSymbol)) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * This function takes Symbol and List type reference as input parameter and
	 * iterate through the loop If Input symbol matches with the Symbol data present
	 * in the list then it returns the index position of that matching parameter.

	 */
	private Share findShare(ArrayList<Share> list, String symbol) {
		for (int i = 0; i < list.size(); i++) {
			if (symbol.equals(list.get(i).getSymbol())) {
				return list.get(i);
			}
		}
		return null;
	}

	/*
	 * Display only the Symbol and Quantity of all the stocks present in the list of
	 * Customers by iterating through the loop.
	 */
	public void displayCustomerPortfolio() {
		System.out.println("Symbol\t  \tQuantity");
		for (int i = 0; i < customerShareList.size(); i++) {
			Share getShareIndex = customerShareList.get(i);
			System.out.println(getShareIndex.getSymbol() + "\t->\t" + getShareIndex.getQuantity());
		}
	}

	/*
	 * THis function ask the customer to enter the symbol of the stock which he
	 * wants to sell If the stock present in the list then all information of the
	 * stock is collected and checks whether that stock is present or not in the
	 * Company list. if present then all information of the stock is collected. Then
	 * customer put how much quantity he wants to sell. If input data matches the
	 * Necessary conditions then the stock is updated from both company list and
	 * customer list. With each success or failure particular error message is
	 * displayed to the customer.
	 */
	public void sellShare() {
		System.out.println("Enter Share sysmbol :");
		String inputSymbol = Util.scanner.next();
		Share foundShareInCompany = findShare(CompanyOperation.companyShareList, inputSymbol);
		if (foundShareInCompany == null) {
			System.out.println(inputSymbol + " share not found in company.");
		}
		int foundCompanyPosition = findShare(companyOperation.getCompanyShareList(), foundShareInCompany);
		if (foundCompanyPosition < 0) {
			System.out.println("company Share " + foundShareInCompany.getSymbol() + " was not found.");
			return;
		}

		Share foundShareInCustomer = findShare(customerShareList, inputSymbol);
		if (foundShareInCustomer == null) {
			System.out.println(inputSymbol + " share not found in found");
		}
		int foundCustomerPosition = findShare(customerShareList, foundShareInCustomer);
		if (foundCustomerPosition < 0) {
			System.out.println("customer Share " + foundShareInCustomer.getSymbol() + " was not found.");
			return;
		}

		System.out.println("Enter Quantity you want to sell :");
		int quantity = Util.scanner.nextInt();
		int updatedCompanyQuantity = foundShareInCompany.getQuantity() + quantity;
		int updatedCustomerQuantity = foundShareInCustomer.getQuantity() - quantity;
		String name = foundShareInCompany.getName();
		String symbol = foundShareInCompany.getSymbol();
		double price = foundShareInCompany.getPrice();
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		Share UpdatedCustomerShare = Share.createShare(name, symbol, price, updatedCustomerQuantity, date, time);
		Share UpdatedCompanyShare = Share.createShare(name, symbol, price, updatedCompanyQuantity, date, time);

		if (foundShareInCustomer.getQuantity() < quantity) {
			System.out.println("Opps! availabe quantity : " + foundShareInCustomer.getQuantity());
		} else if (foundShareInCustomer.getQuantity() == quantity) {
			CompanyOperation.companyShareList.set(foundCompanyPosition, UpdatedCompanyShare);
			customerShareList.remove(foundCustomerPosition);
			System.out.println("all quantity of share " + foundShareInCompany.getSymbol() + " sold.");
		} else {
			CompanyOperation.companyShareList.set(foundCompanyPosition, UpdatedCompanyShare);
			customerShareList.set(foundCustomerPosition, UpdatedCustomerShare);
			System.out
					.println("Sold quantity " + quantity + " remaining quantity " + UpdatedCustomerShare.getQuantity());
		}

	}

}