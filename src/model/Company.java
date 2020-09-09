package model;

import service.Channel;

import java.util.List;

public class Company {

    private String companyName;
    private Channel channel;
    private List<Customer> customers;
    private int emailCount;
    private int smsCount;
    private double totalCost;

    public Company(String companyName, Channel channel, List<Customer> customers, int emailCount, int smsCount, double totalCost) {
        this.companyName = companyName;
        this.customers = customers;
        this.channel = channel;
        this.emailCount = emailCount;
        this.smsCount = smsCount;
        this.totalCost = totalCost;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setEmailCount(int emailCount) {
        this.emailCount = emailCount;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }


    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getEmailCount() {
        return emailCount;
    }

    public void incEmailCnt() {
        this.emailCount++;
    }

    public void incSmsCnt() {
        this.smsCount++;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
