package service;

import exception.SendException;
import model.Company;

public class EmailSabitKotali implements Channel {
    int MAX_EMAIL_COUNT = 1000;
    double cost = 10;

    @Override
    public void send(Company company) {
        for (int i = 0; i < company.getCustomers().size(); i++) {
            if (validateEmailCount(company)) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName());
            } else if (company.getEmailCount() % MAX_EMAIL_COUNT == 0) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName() + "." +
                        "(Paketiniz otomatik olarak yenilenmiştir.)");
            } else
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName());
            company.incEmailCnt();
        }
    }

    @Override
    public void calculateCost(Company company) {
        company.setTotalCost(cost);
        if (!validateEmailCount(company)) {
            int x = company.getEmailCount();
            double new_cost = x / MAX_EMAIL_COUNT * cost;
            if (x % MAX_EMAIL_COUNT != 0)
                new_cost += cost;
            company.setTotalCost(new_cost);
        }
        System.out.format("(EmailSabitKotali)Total cost for " + company.getCompanyName() + " : " + "%.02f", company.getTotalCost());
        System.out.print(" tl");
        System.out.println();
    }

    public boolean validateEmailCount(Company company) {
        return company.getEmailCount() < MAX_EMAIL_COUNT;
    }

    public boolean validate(Company company) throws SendException {
        if (company.getEmailCount() > 3) {
            return true;
        }
        throw new SendException("Paketiniz otomatik olarak yenilenmiştir. ");
    }

}
