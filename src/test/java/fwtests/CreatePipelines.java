package fwtests;

import org.culturegraph.mf.formeta.formatter.FormatterStyle;
import org.culturegraph.mf.stream.converter.FormetaEncoder;
import org.culturegraph.mf.stream.converter.LineReader;
import org.culturegraph.mf.stream.converter.bib.PicaDecoder;
import org.culturegraph.mf.stream.converter.xml.XmlDecoder;
import org.culturegraph.mf.stream.sink.ObjectWriter;
import org.culturegraph.mf.stream.source.FileOpener;
import org.culturegraph.mf.util.FileCompression;

/**
 * Created by swissbib on 22.11.15.
 */
public class CreatePipelines {


    public static void main (String[] args) {

        FileOpener opener = new FileOpener();

        XmlDecoder xmlDecoder = new XmlDecoder();
        MyXmlreceiver myReceiver = new MyXmlreceiver();

        FormetaEncoder encoder = new FormetaEncoder();
        encoder.setStyle(FormatterStyle.MULTILINE);
        ObjectWriter<String> writer = new ObjectWriter<>(
                "src/test/java/fwtests/sample1-out.txt");

        opener.setReceiver(xmlDecoder)//
                .setReceiver(myReceiver)//
                //.setReceiver(encoder)//
                .setReceiver(writer);

        opener.process("src/main/resources/fwtests/myMarc.xml");
        opener.closeStream();


    }



}
