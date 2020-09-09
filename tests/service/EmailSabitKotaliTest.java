package service;

import model.Company;
import model.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmailSabitKotaliTest {
    @Test
    public void it_should_increment_Email_count() {
        //Given
        Customer customer1 = new Customer("ali", "ali@gmail.com", "message", "123456");
        Customer customer2 = new Customer("ayşe", "ayşe@gmail.com", "message2", "123456");
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer1);
        customers.add(customer2);
        Company company = new Company("Trendyol", new EmailSabitKotali(), customers, 0, 0, 0);

        //When
        company.getChannel().send(company);

        //Then
        assertEquals(2, company.getEmailCount());
    }

    @Test
    public void it_should_increment_totalCost() {
        //Given
        List<Customer> customers = new ArrayList<Customer>();
        for (int i = 0; i < 1001; i++) {
            Customer customer = new Customer("Ayse", "ayse@gmail.com", "message", "123456");
            customers.add(customer);
        }

        Company company = new Company("Trendyol", new EmailSabitKotali(), customers, 0, 0, 0);

        //When
        company.getChannel().send(company);
        company.getChannel().calculateCost(company);

        //Then
        assertEquals(20.0, company.getTotalCost(), 0.001);

    }
}