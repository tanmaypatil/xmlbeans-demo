package com.xmldemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.QNameCache;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

/**
 * Unit test for apache xml bean usages This will allow to understand , how
 * apache xmlbeans can be used to parse xml do xpaths , create a xml document
 * etc.
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
        XmlObject xo = x.parseXml("note.xml", noteXml);
        Assertions.assertNotNull(xo);
    }

    @Test
    void getSecondOcurrence() {
        String str = "<we are looking for second occurence of brace< new";
        System.out.println("lelngth " + str.length());
        int len = str.indexOf("<", 0);
        System.out.println("len is " + len);
        int len1 = str.indexOf("<", len + 1);
        System.out.println("len1 is " + len);
        String rem = str.substring(len1 + 1, str.length());
        System.out.println("rem is " + rem);
    }

    @Test
    void getSecondOcurrence1DoesNotwork() {
        String str = "<we are looking for second occurence of brace< new";
        System.out.println("lelngth " + str.length());
        int len = str.indexOf("<", 0);
        System.out.println("len is " + len);
        int len1 = str.indexOf("<", len);
        System.out.println("len1 is " + len);
        String rem = str.substring(len1 + 1, str.length());
        System.out.println("rem is " + rem);
    }

    @Test
    void getSecondOcurrence2Doeswork() {
        String str = "<we are looking for second occurence of brace< new";
        String rem = str.substring(str.indexOf("<", str.indexOf("<", 0) + 1) + 1, str.length());
        System.out.println("rem is :" + rem);
    }

    @Test
    void createXml() {
     
        XmlObject rootDoc = null;
        String sXmlDocumentTemplateString = "<%s xmlns=\"%s\"/>";
        String sRootNodeName = "Document";
        String nameSpace = "http://www.w3.org/2001/XMLSchema";
      
        String out = String.format(sXmlDocumentTemplateString, sRootNodeName, nameSpace);
        System.out.println(" out is " + out);
        XmlUtils x = new XmlUtils();
        x.setBasePath("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo");
        SchemaTypeLoader s = x.readSchema("note.xsd");
        try {
            rootDoc = s.parse(out, null, null);
        } catch (Exception e) {
            System.out.println("exception in parsing namespace");
        }
        Assertions.assertNotNull(rootDoc);
        System.out.println("rootDoc " + rootDoc.toString());
        XmlObjectBase actualRoot = (XmlObjectBase) rootDoc;
       
    
     

        // create a child node 
        TypeStore elementTypeStore = null;
        if (actualRoot != null) {
            elementTypeStore = (TypeStore) actualRoot.get_store();
        }
        Assertions.assertNotNull(elementTypeStore);
        
        String sChildNodeName = "note";
        QName qnmCurrentElementName = new QName(nameSpace, sChildNodeName, "xs");
        XmlObjectBase currentPathElement = (XmlObjectBase) elementTypeStore.find_attribute_user(qnmCurrentElementName);
        if (currentPathElement == null) {
            currentPathElement = (XmlObjectBase) elementTypeStore.add_attribute_user(qnmCurrentElementName);
        }
        Assertions.assertNotNull(currentPathElement);
        System.out.println(" schema "+currentPathElement.schemaType().getName());

    }

    @DisplayName("check if xsd has a specific namespace , mynotes")
    @Test
    void checkForNameSpace() {
        XmlObject rootDoc = null;
        String sXmlDocumentTemplateString = "<%s xmlns=\"%s\"/>";
        String sRootNodeName = "Document";
        String nameSpace = "urn:mynotes";  
        String out = String.format(sXmlDocumentTemplateString, sRootNodeName, nameSpace);
        System.out.println(" out is " + out);
        XmlUtils x = new XmlUtils();
        x.setBasePath("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo");
        SchemaTypeLoader s = x.readSchema("note1.xsd");
        boolean flag = s.isNamespaceDefined(nameSpace);
        Assertions.assertTrue(flag);
  
    }

    @DisplayName("check if xsd has a specific namespace , mynotes")
    @Test
    void createXml2() {
        XmlObject rootDoc = null;
        String sXmlDocumentTemplateString = "<%s xmlns=\"%s\"/>";
        String sRootNodeName = "Document";
        String nameSpace = "urn:mynotes";  
        String out = String.format(sXmlDocumentTemplateString, sRootNodeName, nameSpace);
        System.out.println(" out is " + out);
        XmlUtils x = new XmlUtils();
        x.setBasePath("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo");
        SchemaTypeLoader s = x.readSchema("note1.xsd");
        boolean flag = s.isNamespaceDefined(nameSpace);
        Assertions.assertTrue(flag);
         try {
            rootDoc = s.parse(out, null, null);
        } catch (Exception e) {
            System.out.println("exception in parsing namespace");
        }
        Assertions.assertNotNull(rootDoc);
        System.out.println("rootDoc " + rootDoc.toString());
        XmlObjectBase actualRoot = (XmlObjectBase) rootDoc;
          // create a child node 
        TypeStore elementTypeStore = null;
        if (actualRoot != null) {
            elementTypeStore = (TypeStore) actualRoot.get_store();
        }
        Assertions.assertNotNull(elementTypeStore);
        System.out.println(" actualRoot , schema "+actualRoot.schemaType().getName());
        String sChildNodeName = "note";
        QName qnmCurrentElementName = new QName(nameSpace, sChildNodeName);
        XmlObjectBase currentPathElement = (XmlObjectBase) elementTypeStore.find_attribute_user(qnmCurrentElementName);
        if (currentPathElement == null) {
            currentPathElement = (XmlObjectBase) elementTypeStore.add_attribute_user(qnmCurrentElementName);
        }
        Assertions.assertNotNull(currentPathElement);
        System.out.println(" note ,schema "+currentPathElement.schemaType().getName());
       
    
  
    }

}
