package com.example.demo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Response")
@XmlType(propOrder = { "status", "ticketid", "message", "datetime"})
public class PrintResponse {
	private String status;
	private String ticketid;
	private Date datetime;
	private String message;
	public PrintResponse() {
		
	}
	public PrintResponse(String ticketid) {
		this.ticketid = ticketid;
		this.status = "success";
		this.datetime = new Date();
	}
	@XmlAttribute
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@XmlElement(name="ticketid")
	public String getTicketid() {
		return ticketid;
	}
	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	@XmlElement(name="datetime")
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	@XmlElement(name="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
