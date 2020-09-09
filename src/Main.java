import model.Company;
import model.Customer;
import service.EmailEsnekKotali;
import service.EmailSabitKotali;
import service.SmsEsnekKotali;
import service.SmsSabitKotali;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Firmanın oluşturacağı gönderim listesi
        Customer customer1 = new Customer("Ali", "ali@gmail.com", "message", "123456");
        Customer customer2 = new Customer("Ayşe", "ayşe@gmail.com", "message2", "123456");
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer1);
        customers.add(customer2);

        Company company = new Company("Trendyol", new SmsEsnekKotali(), customers, 0, 0, 0);
        company.getChannel().send(company);
        company.getChannel().calculateCost(company);

        System.out.println();

        Company company2 = new Company("Dolap", new SmsSabitKotali(), customers, 0, 0, 0);
        company2.getChannel().send(company2);
        company2.getChannel().calculateCost(company2);
    }
}
