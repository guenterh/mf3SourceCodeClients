package org.swissbib.nl.reader.oxford;

import org.culturegraph.mf.exceptions.MetafactureException;
import org.culturegraph.mf.framework.DefaultXmlPipe;
import org.culturegraph.mf.framework.StreamReceiver;
import org.culturegraph.mf.stream.converter.xml.GenericXmlHandler;
import org.culturegraph.mf.stream.reader.GenericXmlReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.regex.Pattern;

/**
 * Created by swissbib on 22.11.15.
 */
public class NLDTDHandler extends DefaultXmlPipe<StreamReceiver> {

    private static final Pattern TABS = Pattern.compile("\t+");
    private final String recordTagName;
    private boolean inRecord;
    private StringBuilder valueBuffer = new StringBuilder();

    public NLDTDHandler() {
        super();
        this.recordTagName = System.getProperty("org.culturegraph.metamorph.xml.recordtag");
        if (recordTagName == null) {
            throw new MetafactureException("Missing name for the tag marking a record.");
        }
    }

    public NLDTDHandler(final String recordTagName) {
        super();
        this.recordTagName = recordTagName;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
            throws SAXException {

        if (inRecord) {
            writeValue();
            getReceiver().startEntity(localName);
            writeAttributes(attributes);
        } else if (localName.equals(recordTagName)) {
            final String identifier = attributes.getValue("id");
            if (identifier == null) {
                getReceiver().startRecord("");
            } else {
                getReceiver().startRecord(identifier);
            }
            writeAttributes(attributes);
            inRecord = true;
        }
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (inRecord) {
            writeValue();
            if (localName.equals(recordTagName)) {
                inRecord = false;
                getReceiver().endRecord();
            } else {
                getReceiver().endEntity();
            }
        }
    }

    @Override
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        if (inRecord) {
            valueBuffer.append(TABS.matcher(new String(chars, start, length)).replaceAll(""));
        }
    }

    private void writeValue() {
        final String value = valueBuffer.toString();
        if (!value.trim().isEmpty()) {
            getReceiver().literal("value", value.replace('\n', ' '));
        }
        valueBuffer = new StringBuilder();
    }

    private void writeAttributes(final Attributes attributes) {
        final int length = attributes.getLength();

        for (int i = 0; i < length; ++i) {
            final String name = attributes.getLocalName(i);
            final String value = attributes.getValue(i);
            getReceiver().literal(name, value);
        }
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId)
            throws SAXException {
        String t = "test";
    }

    @Override
    public void unparsedEntityDecl(final String name, final String publicId,
                                   final String systemId, final String notationName) throws SAXException {
        String t = "test";
    }


}
