package service;

import exception.SendException;
import model.Company;

public class EmailEsnekKotali implements Channel {
    private int MAX_EMAIL_COUNT = 2000;
    private double cost = 7.5;


    @Override
    public void send(Company company) {

        for (int i = 0; i < company.getCustomers().size(); i++) {
            if (validateEmailCount(company)) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName());
            } else if (company.getEmailCount() % MAX_EMAIL_COUNT == 0) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName() + "." +
                        "(Paketiniz bitmiştir. Her email 0,03 tl olarak ücretlendirilecek.)");
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
            double new_cost = (x - MAX_EMAIL_COUNT) * 0.03 + cost;
            company.setTotalCost(new_cost);
        }
        System.out.format("(EmailEsnekKotali)Total cost for " + company.getCompanyName() + " : " + "%.02f", company.getTotalCost());
        System.out.println();

    }

    public boolean validateEmailCount(Company company) {
        return company.getEmailCount() < MAX_EMAIL_COUNT;

    }

    public boolean validate(Company company) throws SendException {
        if (company != null) {
            return true;
        }
        throw new SendException("Paketiniz bitmiştir. Her email 0,03 tl olarak ücretlendirilecek.");
    }

}
