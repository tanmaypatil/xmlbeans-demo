package com.xmldemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

public class XmlUtils {

    private String basePath = "";

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public SchemaTypeLoader readSchema(String xsdFile) {
        SchemaTypeLoader m_contextXchemaTypeLoader = null;
        try {
            String finalPath = this.basePath + "\\" + xsdFile;
            List<XmlObject> listSchemaFiles = new ArrayList<XmlObject>();
            File schemaFile = null;
            schemaFile = new File(finalPath);
            if (schemaFile.exists()) {
                listSchemaFiles.add(XmlObject.Factory.parse(schemaFile));
            }
            m_contextXchemaTypeLoader
                    = XmlBeans.compileXsd(listSchemaFiles.toArray(new XmlObject[]{}), XmlBeans.getContextTypeLoader(),
                            new XmlOptions().setLoadMessageDigest());
            m_contextXchemaTypeLoader
                    = XmlBeans.typeLoaderUnion(new SchemaTypeLoader[]{m_contextXchemaTypeLoader, XmlBeans.getContextTypeLoader()});
        } catch (Exception e) {
            System.out.println("exception in schema compilation " + xsdFile);
            System.out.println("exception  : " + e);
        }
        return m_contextXchemaTypeLoader;

    }

}
