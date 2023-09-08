package com.xmldemo;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NativeXmlUtils {
    DocumentBuilder builder = null;
    Document document = null;

    public NativeXmlUtils() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setNamespaceAware(true);
            builder = factory.newDocumentBuilder();
       
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    public NativeXmlUtils getDocument(String name) throws SAXException, IOException {
        this.document = builder.parse(new File(name));
        return this;
    }

     public NativeXmlUtils getDocumentFromStr(String xmlDoc) throws SAXException, IOException {
        this.document = builder.parse(new InputSource( new StringReader( xmlDoc ) ));
        return this;
    }

    // reads the first node.
    public String getValueOfTag(String xpath) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        xPath.setNamespaceContext(new NameSpaceResolver(this.document));
        NodeList nodeList = (NodeList) xPath.compile(xpath).evaluate(this.document, XPathConstants.NODESET);
        int i = nodeList.getLength();
        Node n = nodeList.item(0);
        String s = n.getTextContent();
        return s;
    }

    //if xpath's result are multiple nodes , return list
    public List<String> getMultiValueOfTag(String xpath) throws XPathExpressionException {
        ArrayList<String> list = new ArrayList<String>();
        XPath xPath = XPathFactory.newInstance().newXPath();
        xPath.setNamespaceContext(new NameSpaceResolver(this.document));
        NodeList nodeList = (NodeList) xPath.compile(xpath).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            String s = n.getTextContent();
            list.add(s);
        }
        return list;
    }

    public String parseXmlFile(String name) throws SAXException, IOException {
        Document document = builder.parse(new File(name));
        String ns = document.getNamespaceURI();
        // String prefix = document.getPrefix();
        NodeList n = document.getChildNodes();
        for (int j = 0; j < n.getLength(); j++) {
            Node n1 = n.item(j);
            String s = n1.getNamespaceURI();
            System.out.println(" namespace upper loop" + j + s);
            String s1 = n1.getTextContent();
            System.out.println(" text content upper loop" + j + s1);
            NodeList n2 = n1.getChildNodes();
            for (int i = 0; i < n2.getLength(); i++) {
                Node n3 = n2.item(i);
                String t1 = n3.getTextContent();
                System.out.println(" text content " + i + t1);
                String ns1 = n3.getNamespaceURI();
                System.out.println(" namespace " + i + ns1);

            }

        }
        return ns;
    }

}
