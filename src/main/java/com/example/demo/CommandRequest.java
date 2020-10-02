package com.example.demo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Request")
@XmlType(propOrder = { "command", "ticketid"})
public class CommandRequest {
	private String command;
	private String ticketid;

	@XmlAttribute
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@XmlElement(name="ticketid")
	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	
}
