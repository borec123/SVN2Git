package com.example.consumingwebservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author Petr Heczko
 */
public class WSDLParser {

  String getURLFromWSDL(String wsdl) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = dbf.newDocumentBuilder();

    URL url = new URL(wsdl);
    InputStream stream = url.openStream();
    Document doc = docBuilder.parse(stream);

    XPath xPath =  XPathFactory.newInstance().newXPath();

    String expression = "/definitions/service/port/address/@location";
    NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
      doc, XPathConstants.NODESET);

    return nodeList.item(0).getNodeValue();
  }
}

