package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class XMLController {

	@RequestMapping(value="/")
	public void parseDoc(HttpServletRequest req, HttpServletResponse res) throws IOException, JAXBException {
		try {
			String command = URLDecoder.decode(req.getQueryString(),StandardCharsets.UTF_8);
			StringReader reader = new StringReader(command);
			//System.out.println(command);
			//if(true) return;
			JAXBContext context = JAXBContext.newInstance(CommandRequest.class);
			CommandRequest requestObj = (CommandRequest) context.createUnmarshaller().unmarshal(reader);
			switch(requestObj.getCommand().toLowerCase()) {
				case "print":
					PrintResponse response = new PrintResponse(requestObj.getTicketid());
					JAXBContext resContext = JAXBContext.newInstance(PrintResponse.class);
					Marshaller marshal = resContext.createMarshaller();
					marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					marshal.marshal(response, res.getOutputStream());
				break;
				default:
					PrintResponse failResponse = new PrintResponse();
					failResponse.setStatus("Failed.");
					failResponse.setMessage("Invalid Command.");
					JAXBContext failContext = JAXBContext.newInstance(PrintResponse.class);
					Marshaller failMarshal = failContext.createMarshaller();
					failMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					failMarshal.marshal(failResponse, res.getOutputStream());
					
			}
		} catch (JAXBException exception) {
			PrintResponse invalidXMLResponse = new PrintResponse();
			invalidXMLResponse.setStatus("Failed");
			invalidXMLResponse.setMessage("Invalid XML");
			JAXBContext invalidContext = JAXBContext.newInstance(PrintResponse.class);
			Marshaller invalidMarshal = invalidContext.createMarshaller();
			invalidMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			invalidMarshal.marshal(invalidXMLResponse, res.getOutputStream());
		}
	}
	
}
