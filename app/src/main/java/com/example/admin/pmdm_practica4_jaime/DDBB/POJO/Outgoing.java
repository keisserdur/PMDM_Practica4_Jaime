package com.example.admin.pmdm_practica4_jaime.DDBB.POJO;

/**
 * Created by Admin on 02/02/2016.
 */
public class Outgoing {
    private String number,date,time;
    long id;

    public Outgoing() {
    }

    public Outgoing(String number, String date, String time, long id) {
        this.number = number;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Incoming{" +
                "number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", id=" + id +
                '}';
    }
}
