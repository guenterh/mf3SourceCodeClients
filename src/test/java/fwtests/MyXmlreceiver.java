package fwtests;

import org.culturegraph.mf.framework.DefaultXMLReceiver;
import org.culturegraph.mf.framework.DefaultXmlPipe;
import org.culturegraph.mf.framework.ObjectReceiver;
import org.culturegraph.mf.framework.StreamReceiver;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.IOException;

/**
 * Created by swissbib on 22.11.15.
 */
public class MyXmlreceiver  extends DefaultXmlPipe<ObjectReceiver<String>>{

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        super.startPrefixMapping(prefix, uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        super.endPrefixMapping(prefix);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        super.startElement(uri, localName, qName, atts);
        getReceiver().process(localName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        super.characters(chars, start, length);
    }

    @Override
    public void ignorableWhitespace(char[] chars, int start, int length) throws SAXException {
        super.ignorableWhitespace(chars, start, length);
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        super.processingInstruction(target, data);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        super.skippedEntity(name);
    }

    @Override
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        super.notationDecl(name, publicId, systemId);
    }

    @Override
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        super.unparsedEntityDecl(name, publicId, systemId, notationName);
    }

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        super.warning(exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        super.error(exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        super.fatalError(exception);
    }

    @Override
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
        super.startDTD(name, publicId, systemId);
    }

    @Override
    public void endDTD() throws SAXException {
        super.endDTD();
    }

    @Override
    public void startEntity(String name) throws SAXException {
        super.startEntity(name);
    }

    @Override
    public void endEntity(String name) throws SAXException {
        super.endEntity(name);
    }

    @Override
    public void startCDATA() throws SAXException {
        super.startCDATA();
    }

    @Override
    public void endCDATA() throws SAXException {
        super.endCDATA();
    }

    @Override
    public void comment(char[] chars, int start, int length) throws SAXException {
        super.comment(chars, start, length);
    }
}
