package com.xmldemo;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import org.w3c.dom.Document;

public class NameSpaceResolver implements NamespaceContext {
    private Document sourceDocument;

    public NameSpaceResolver(Document document) {
        sourceDocument = document;
    }

    // The lookup for the namespace uris is delegated to the stored document.
    public String getNamespaceURI(String prefix) {
        System.out.println("prefix is " + prefix);
        String ns = null;
        if (prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
            return sourceDocument.lookupNamespaceURI(null);
        } else {
            ns = sourceDocument.lookupNamespaceURI(prefix);
            System.out.println("namespace is "+ns);
            return ns ;
        }
    }

    public String getPrefix(String namespaceURI) {
        return sourceDocument.lookupPrefix(namespaceURI);
    }

    @SuppressWarnings("rawtypes")
    public Iterator getPrefixes(String namespaceURI) {
        return null;
    }
}
