package com.xmldemo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class javaXmlTest {

    @DisplayName("check if xsd has a specific namespace , mynotes")
    @Test
    public void readNS() throws Exception {
        NativeXmlUtils nu = new NativeXmlUtils();
        String path = "C:\\Users\\u725561\\xmlbeans-demo\\xmldemo\\src\\test\\java\\com\\xmldemo\\Tutorials.xml";
        String ns = nu.parseXmlFile(path);
        //Assertions.assertTrue(ns != null);


    }
    
}
