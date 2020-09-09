package service;

import exception.SendException;
import model.Company;

public class SmsEsnekKotali implements Channel {
    private int MAX_SMS_COUNT = 2000;
    private double cost = 30;


    @Override
    public void send(Company company) {

        for (int i = 0; i < company.getCustomers().size(); i++) {
            if (validateSmsCount(company)) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName());
            } else if (company.getEmailCount() % MAX_SMS_COUNT == 0) {
                System.out.println("Sending email from " + company.getCompanyName() + " to " + company.getCustomers().get(i).getCustomerName() + "." +
                        "(Paketiniz bitmiştir. Her SMS 0,10 tl olarak ücretlendirilecek.)");
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
            double new_cost = (x - MAX_SMS_COUNT) * 0.10 + cost;
            company.setTotalCost(new_cost);
        }
        System.out.format("(SmsEsnekKotali)Total cost for " + company.getCompanyName() + " : " + "%.02f", company.getTotalCost());
        System.out.println();

    }

    public boolean validateSmsCount(Company company) {
        return company.getSmsCount() < 2000;

    }

    public boolean validate(Company company) throws SendException {
        if (company.getSmsCount() > 2000) {
            return true;
        }
        throw new SendException("Paketiniz bitmiştir. Her SMS 0,10 tl olarak ücretlendirilecek.");
    }


}
