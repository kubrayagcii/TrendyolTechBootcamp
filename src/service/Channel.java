package service;

import model.Company;

public interface Channel {
    void send(Company company);
    void calculateCost(Company company);
}
