package service;

import model.Company;
import model.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SmsEsnekKotaliTest {

    @Test
    public void it_should_increment_Email_count() {
        //Given
        Customer customer1 = new Customer("Ali", "ali@gmail.com", "message", "123456");
        Customer customer2 = new Customer("Ayşe", "ayşe@gmail.com", "message2", "123456");
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer1);
        customers.add(customer2);
        Company company = new Company("Trendyol", new SmsSabitKotali(), customers, 0, 0, 0);

        //When
        company.getChannel().send(company);

        //Then
        assertEquals(2, company.getEmailCount());
    }

    @Test
    public void it_should_increment_totalCost() {
        //Given
        List<Customer> customers = new ArrayList<Customer>();
        for (int i = 0; i < 2001; i++) {
            Customer customer = new Customer("ayse", "ayse@gmail.com", "message", "123456");
            customers.add(customer);
        }

        Company company = new Company("Trendyol", new SmsEsnekKotali(), customers, 0, 0, 0);

        //When
        company.getChannel().send(company);
        company.getChannel().calculateCost(company);

        //Then
        assertEquals(30.1, company.getTotalCost(), 0.001);
    }

}