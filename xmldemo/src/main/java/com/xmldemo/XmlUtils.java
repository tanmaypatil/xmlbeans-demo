package com.xmldemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

public class XmlUtils {

    private String basePath = "";
    SchemaTypeLoader m_contextXchemaTypeLoader = null;

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public SchemaTypeLoader readSchema(String xsdFile) {

        try {
            String finalPath = this.basePath + "\\" + xsdFile;
            List<XmlObject> listSchemaFiles = new ArrayList<XmlObject>();
            File schemaFile = null;
            schemaFile = new File(finalPath);
            if (schemaFile.exists()) {
                listSchemaFiles.add(XmlObject.Factory.parse(schemaFile));
            }
            this.m_contextXchemaTypeLoader
                    = XmlBeans.compileXsd(listSchemaFiles.toArray(new XmlObject[]{}), XmlBeans.getContextTypeLoader(),
                            new XmlOptions().setLoadMessageDigest());
            this.m_contextXchemaTypeLoader
                    = XmlBeans.typeLoaderUnion(new SchemaTypeLoader[]{m_contextXchemaTypeLoader, XmlBeans.getContextTypeLoader()});
        } catch (Exception e) {
            System.out.println("exception in schema compilation " + xsdFile);
            System.out.println("exception  : " + e);
        }
        return m_contextXchemaTypeLoader;
    }

    public String readXml(String xmlFile) {
        String finalStr = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.basePath + "\\" + xmlFile));
            System.out.println("opening " + xmlFile);
            String curLine;

            while ((curLine = bufferedReader.readLine()) != null) {
                System.out.println(curLine);
                finalStr += curLine;
            }
            System.out.println("final Xml " + finalStr);
        } catch (Exception e) {
            System.out.println("exception in reading xml" + e);
        }
        return finalStr;
    }

    public XmlObject parseXml(String name, String xml) {
        XmlObject x = null;
        try {
            x = m_contextXchemaTypeLoader.parse(xml, null, null);
        } catch (Exception e) {
            System.out.println("parsing exception for : " + name);
            System.out.println("parseXml " + e);
        }
        return x;
    }

}
