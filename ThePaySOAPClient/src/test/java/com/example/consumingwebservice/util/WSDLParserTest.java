package com.example.consumingwebservice.util;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

/**
 * @author Petr Heczko
 */
public class WSDLParserTest {

  @Test
  public void testGetURLFromWSDL()  throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
    String wsdl = "https://www.thepay.cz/sender-demo-gate/api/payments-api-demo.wsdl";
    WSDLParser parser = new WSDLParser();
    assertEquals("https://www.thepay.cz/sender-demo-gate/api/payments/?v=2", parser.getURLFromWSDL(wsdl));
  }
}
