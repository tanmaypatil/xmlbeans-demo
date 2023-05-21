package com.xmldemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlObject;

/**
 * Unit test for apache xml bean usages
 * This will allow to understand , how apache xmlbeans can be used to parse xml 
 * do xpaths , create a xml document etc.
 */
class AppTest {

    @Test 
    void loadxsd1() {
        XmlUtils x = new XmlUtils();
        x.setBasePath("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo");
        SchemaTypeLoader s = x.readSchema("shiporder.xsd");
        Assertions.assertNotNull(s);
    }

    @Test 
    void loadxsd2() {
        XmlUtils x = new XmlUtils();
        x.setBasePath("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo");
        SchemaTypeLoader s = x.readSchema("note.xsd");
        Assertions.assertNotNull(s);
    }

    
    @Test 
    @DisplayName("parse note.xml using note.xsd and get XmlObject")
    void parseNotesXml() {
        XmlUtils x = new XmlUtils();
        x.setBasePath("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo");
        SchemaTypeLoader s = x.readSchema("note.xsd");
        Assertions.assertNotNull(s);
        String noteXml = x.readXml("note.xml");
        XmlObject xo = x.parseXml("note.xml",noteXml);
        Assertions.assertNotNull(xo);
    }

}
