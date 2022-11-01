package com.example.tripmanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Backup implements Serializable {
    protected Date _date;
    protected String _deviceName;
    protected ArrayList<Trip> _trips;
    protected ArrayList<Expense> _expenses;

    public Backup(Date date, String deviceName, ArrayList<Trip> trips, ArrayList<Expense> expenses) {
        _date = date;
        _deviceName = deviceName;
        _trips = trips;
        _expenses = expenses;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public Date getDate() {
        return _date;
    }

    public void setDeviceName(String deviceName) {
        _deviceName = deviceName;
    }

    public String getDeviceName() {
        return _deviceName;
    }

    public void setTrips(ArrayList<Trip> trips) {
        _trips = trips;
    }

    public ArrayList<Trip> getTrips() {
        return _trips;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        _expenses = expenses;
    }

    public ArrayList<Expense> getExpenses() {
        return _expenses;
    }
}