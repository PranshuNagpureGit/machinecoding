package model;

import java.util.Date;

public class Payment extends BaseModel{
    private double amount;
    private Bill bill;
    private long paymentReferenceNumber;
    private Date date;
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;
}
