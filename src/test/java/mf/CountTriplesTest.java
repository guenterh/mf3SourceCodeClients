package mf;

import org.culturegraph.mf.morph.Metamorph;
import org.culturegraph.mf.stream.converter.ObjectTemplate;
import org.culturegraph.mf.stream.converter.StreamToTriples;
import org.culturegraph.mf.stream.converter.xml.MarcXmlHandler;
import org.culturegraph.mf.stream.converter.xml.XmlDecoder;
import org.culturegraph.mf.stream.pipe.sort.AbstractTripleSort;
import org.culturegraph.mf.stream.pipe.sort.TripleCount;
import org.culturegraph.mf.stream.pipe.sort.TripleSort;
import org.culturegraph.mf.stream.sink.ObjectWriter;
import org.culturegraph.mf.stream.source.FileOpener;
import org.culturegraph.mf.types.Triple;


/**
 * Created by swissbib on 10.12.15.
 */
public class CountTriplesTest {


    public static void main (String[] args) {
/*

//default in = FLUX_DIR + "../item/correctMarcXML.xml";
default in = FLUX_DIR + "correctMarcXML.xml";
default out = "stdout";

in|
open-file|
decode-xml|
handle-marcxml|
morph(FLUX_DIR + "itemMorph.xml")|
stream-to-triples|
count-triples(countBy="predicate")|
sort-triples(order="increasing")|
// for console use: write(out);
// for triples use: write(FLUX_DIR + "prov_stat_output.rdf");
//
template("${s}\t${o}")| write(FLUX_DIR + "itemOutput.csv");



 */
        FileOpener fo = new FileOpener();
        XmlDecoder xmlDecoder = new XmlDecoder();
        MarcXmlHandler marcXmlHandler = new MarcXmlHandler();
        Metamorph morph = new Metamorph("./src/main/resources/statistics/itemMorph.xml");
        StreamToTriples streamToTriples = new StreamToTriples();
        TripleCount tripleCount = new TripleCount();
        tripleCount.setCountBy(AbstractTripleSort.Compare.OBJECT);
        TripleSort tripleSort = new TripleSort();
        tripleSort.setOrder(AbstractTripleSort.Order.DECREASING);
        ObjectTemplate<Triple> objectTemplate = new ObjectTemplate<>("${s} | ${o}");
//        ObjectWriter<String> objectWriter = new ObjectWriter<>("./src/main/resources/statistics/testCompare.txt");
        ObjectWriter<String> objectWriter = new ObjectWriter<>("stdout");

        tripleCount.memoryLow(10000,1000000);

        fo.setReceiver(xmlDecoder)
                .setReceiver(marcXmlHandler)
                .setReceiver(morph)
                .setReceiver(streamToTriples)
                .setReceiver(tripleCount)
                .setReceiver(tripleSort)
                .setReceiver(objectTemplate)
                .setReceiver(objectWriter);



        fo.process("./src/main/resources/statistics/correctMarcXML.xml");
        fo.closeStream();


    }


}
