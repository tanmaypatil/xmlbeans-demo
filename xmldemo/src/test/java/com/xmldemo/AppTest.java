package com.xmldemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

/**
 * Unit test for simple App.
 */
class AppTest {

    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        assertEquals(1, 1);
    }

    @Test
    void testLoadSchema() {
        SchemaTypeLoader m_contextXchemaTypeLoader;
        try {
            List<XmlObject> listSchemaFiles = new ArrayList<XmlObject>();
            File schemaFile = null;
            schemaFile = new File("./shiporder.xsd");
            if (schemaFile.exists()) {
                listSchemaFiles.add(XmlObject.Factory.parse(schemaFile));
            }
            m_contextXchemaTypeLoader
                    = XmlBeans.compileXsd(listSchemaFiles.toArray(new XmlObject[]{}), XmlBeans.getContextTypeLoader(),
                            new XmlOptions().setLoadMessageDigest());
            m_contextXchemaTypeLoader
                    = XmlBeans.typeLoaderUnion(new SchemaTypeLoader[]{m_contextXchemaTypeLoader, XmlBeans.getContextTypeLoader()});
            
            // Check whether so namespace exists 
            boolean isExists = m_contextXchemaTypeLoader.isNamespaceDefined("so");
            assertEquals(true,isExists);
            
            //isExists = m_contextXchemaTypeLoader.isNamespaceDefined("sa");
            //assertEquals(true,isExists);

        } catch (Exception e) {
            System.out.println("exception in shiporder xsd parsing"+e);
        }

    }
@Test
    void testApp2() {
        SchemaTypeLoader m_contextXchemaTypeLoader;
        BufferedReader bufferedReader = null;
        try {
            List<XmlObject> listSchemaFiles = new ArrayList<XmlObject>();
            File schemaFile = null;
            schemaFile = new File("./note.xsd");
            if (schemaFile.exists()) {
                listSchemaFiles.add(XmlObject.Factory.parse(schemaFile));
            }
            m_contextXchemaTypeLoader
                    = XmlBeans.compileXsd(listSchemaFiles.toArray(new XmlObject[]{}), XmlBeans.getContextTypeLoader(),
                            new XmlOptions().setLoadMessageDigest());
            m_contextXchemaTypeLoader
                    = XmlBeans.typeLoaderUnion(new SchemaTypeLoader[]{m_contextXchemaTypeLoader, XmlBeans.getContextTypeLoader()});
           
           
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo\\note.xml"));
            System.out.println("opening note.xml" );
            String curLine;
            String finalStr = "";
            while ((curLine = bufferedReader.readLine()) != null){
                System.out.println(curLine);
                finalStr += curLine;
            }
            System.out.println("final Xml "+finalStr);
            XmlObject x = m_contextXchemaTypeLoader.parse(finalStr, null, null);
            boolean y = x.isImmutable();
            System.out.println( "value of y"+y);
            XmlCursor z = x.newCursor();
           XmlObject o = z.getObject();
           if ( o != null) {
            System.out.println("XmlObject is not null");
           }
    
           XmlObject xa = o.selectAttribute(new QName("http://www.w3.org/TR/html4/",   "h"));
           if ( xa == null) {
            final XmlCursor xmlcur = o.newCursor();
            xmlcur.toStartDoc();
            xmlcur.toFirstChild();
            xa = xmlcur.getObject();
            xa = o.selectAttribute(new QName("http://www.w3.org/TR/html4/",   "h"));
           }
           String uri = xa.schemaType().getName().getNamespaceURI();
           System.out.println("uri is "+uri);

        } catch (Exception e) {
            System.out.println("exception in shiporder xsd parsing"+e);
        }
        finally{
            try {
            bufferedReader.close();
            }
            catch(Exception e) {
                System.out.println("closing error in bufferedreader "+e);

            }
        }

    }
}
