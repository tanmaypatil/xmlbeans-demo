package com.xmldemo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class XPathTest {

    @Test
    @DisplayName("use xpath to fetch xml first node content")
    public void fetchXml() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        NativeXmlUtils n = new NativeXmlUtils();
        String s = n
                .getDocument("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo\\Tutorials.xml")
                .getValueOfTag("/Tutorials/Tutorial/title");
        Assertions.assertEquals("Guava", s);
        System.out.println("fetched title " + s);

    }

    @Test
    @DisplayName("use xpath to fetch xml all nodes")
    public void fetchMultiXmlNodes() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        NativeXmlUtils n = new NativeXmlUtils();
        List<String> expected = Arrays.asList(new String[]{"Guava", "XML"});
        List<String> s = n
                .getDocument("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo\\Tutorials.xml")
                .getMultiValueOfTag("/Tutorials/Tutorial/title");
        //Assertions.assertEquals("Guava", s);
        System.out.println("fetched title " + s.size());
        Assertions.assertEquals(expected, s);
    }

    @Test
    @DisplayName("use xpath to fetch node with string source")
    public void fetchXPathusingStr() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        String xml =" <todo>" +
        "<date>2023-09-08</date>" +
        "<description> study java xml api</description>" +
        "</todo>";
         NativeXmlUtils n = new NativeXmlUtils();
         String date = n.getDocumentFromStr(xml).getValueOfTag("/todo/date");
         System.out.println(date);

    }

    
    @Test
    @DisplayName("use xpath to fetch first node content from xml with namespace")
    public void fetchXmlNodeWithNs() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        NativeXmlUtils n = new NativeXmlUtils();
        List<String> s = n
                .getDocument("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo\\Employee.xml")
                .getMultiValueOfTag("//h:employees/h:employee/h:firstName");
        //Assertions.assertEquals("Guava", s);
        System.out.println("fetched firstnames " + s);

    }

}
