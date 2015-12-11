package org.swissbib.nl.reader.oxford;

import org.culturegraph.mf.stream.converter.xml.GenericXmlHandler;
import org.culturegraph.mf.stream.reader.XmlReaderBase;

/**
 * Created by swissbib on 22.11.15.
 */
public class ArticleReader extends XmlReaderBase {

    public ArticleReader () {
        super(new GenericXmlHandler("article"));
    }




}
