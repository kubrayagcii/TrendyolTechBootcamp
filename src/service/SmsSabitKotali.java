package service;

import exception.SendException;
import model.Company;

public class SmsSabitKotali implements Channel {
    int MAX_SMS_COUNT = 1000;
    double cost = 20;

    @Override
    public void send(Company company) {
        for (int i = 0; i < company.getCustomers().size(); i++) {
            if (validateSmsCount(company)) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName());
            } else if (company.getEmailCount() % MAX_SMS_COUNT == 0) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName() + "." +
                        "(Paketiniz otomatik olarak yenilenmiştir.)");
            } else
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName());

            company.incSmsCnt();
        }
    }

    @Override
    public void calculateCost(Company company) {
        company.setTotalCost(cost);
        if (!validateSmsCount(company)) {
            int x = company.getSmsCount();
            double new_cost = x / MAX_SMS_COUNT * 20;
            if (x % MAX_SMS_COUNT != 0)
                new_cost += cost;
            company.setTotalCost(new_cost);
        }
        System.out.format("(SmsSabitKotali)Total cost for " + company.getCompanyName() + " : " + "%.02f", company.getTotalCost());
        System.out.print(" tl");
        System.out.println();
    }

    public boolean validateSmsCount(Company company) {
        return company.getSmsCount() < MAX_SMS_COUNT;
    }

    public boolean validate(Company company) throws SendException {
        if (company.getSmsCount() > 1000) {
            return true;
        }
        throw new SendException("Paketiniz otomatik olarak yenilenmiştir. ");
    }
}
