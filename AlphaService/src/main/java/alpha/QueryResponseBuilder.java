package alpha; /**
 * Created by Alankrit on 03-Feb-17.
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

public class QueryResponseBuilder {
    private static final Logger logger = Logger.getLogger(QueryResponseBuilder.class.getName());
    public static String getResponseForQuery (String query) {
        try {
            String xmlResponse = AlphaContacter.fetchResult(query);
            return new QueryResponse(query,buildResponseFromXML(xmlResponse)).toString();
        } catch (Exception e) {
            logger.info("Invalid query : " + query);
        }
        return new QueryResponse(query, buildFailureResponse(query)).toString();
    }

    public static String getResponseForQueryAsString (String query) {
        try {
            String xmlResponse = AlphaContacter.fetchResult(query);
            return buildResponseFromXML(xmlResponse);
        } catch (Exception e) {
            logger.info("Invalid query : " + query);
        }
        return buildFailureResponse(query);
    }

    @Test
    public void mathTest () {
        System.out.println(getResponseForQuery("35*3"));
    }
    public static String buildResponseFromXML (String xml) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new StringBufferInputStream(xml));
        doc.getDocumentElement().normalize();
        //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("pod");
        //System.out.println("List length is : " + nList.getLength());
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            String title = nNode.getAttributes().getNamedItem("title").getNodeValue();
            NodeList nodeList = nNode.getChildNodes();
            int i ;
            StringBuilder stringBuilder1 = new StringBuilder();
            for (i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                //System.out.println(title.trim());
                String textContent = node.getTextContent();
                String trimmedContent = textContent.trim();
                if (trimmedContent.length() > 0) {
                    stringBuilder1.append(trimmedContent);
                    stringBuilder1.append("\n");
                }
            }
            if (stringBuilder1.length() > 0) {
                stringBuilder.append(title);
                stringBuilder.append("\n");
                stringBuilder.append(stringBuilder1);
            }
        }
        if (stringBuilder.length() == 0) {
            throw new Exception("Error Response");
        }
        return stringBuilder.toString();
    }

    @Test
    public void parsexmlresponseTest () throws Exception {
        String xml = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<queryresult success=\"true\" error=\"false\" numpods=\"9\" datatypes=\"AdministrativeDivision,City,Country,Leader,People,WikipediaStats\" timedout=\"\" timedoutpods=\"\" timing=\"4.926\" parsetiming=\"0.242\" parsetimedout=\"false\" recalculate=\"\" id=\"MSPa74211c8a8ca4cgb8353000001g1b48748bfd51b3\" host=\"http://www4b.wolframalpha.com\" server=\"61\" related=\"http://www4b.wolframalpha.com/api/v2/relatedQueries.jsp?id=MSPa74221c8a8ca4cgb835300000247b8e7911g812gg&amp;redisFailed=true&amp;s=61\" version=\"2.6\">\n" +
                "    <pod title=\"Input interpretation\" scanner=\"Identity\" id=\"Input\" position=\"100\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74231c8a8ca4cgb8353000004g96fd6g23i43gci?MSPStoreType=image/gif&amp;s=61\" alt=\"Atal Bihari Vajpayee  (politician)\" title=\"Atal Bihari Vajpayee  (politician)\" width=\"213\" height=\"18\" />\n" +
                "            <plaintext>Atal Bihari Vajpayee  (politician)</plaintext>\n" +
                "        </subpod>\n" +
                "    </pod>\n" +
                "    <pod title=\"Basic information\" scanner=\"Data\" id=\"BasicInformation:PeopleData\" position=\"200\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74241c8a8ca4cgb83530000035fbf9a7aecad07e?MSPStoreType=image/gif&amp;s=61\" alt=\"full name | Atal Bihari Vajpayee date of birth | Thursday, December 25, 1924 (age: 92 years) place of birth | Gwalior, Madhya Pradesh\" title=\"full name | Atal Bihari Vajpayee date of birth | Thursday, December 25, 1924 (age: 92 years) place of birth | Gwalior, Madhya Pradesh\" width=\"434\" height=\"100\" />\n" +
                "            <plaintext>full name | Atal Bihari Vajpayee\n" +
                "                date of birth | Thursday, December 25, 1924 (age: 92 years)\n" +
                "                place of birth | Gwalior, Madhya Pradesh</plaintext>\n" +
                "        </subpod>\n" +
                "    </pod>\n" +
                "    <pod title=\"Image\" scanner=\"Data\" id=\"Image:PeopleData\" position=\"300\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <imagesource>http://en.wikipedia.org/wiki/File:Atal_Bihari_Vajpayee.jpg</imagesource>\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74251c8a8ca4cgb8353000005769i0dbbbhdb41i?MSPStoreType=image/gif&amp;s=61\" alt=\"\" title=\"\" width=\"92\" height=\"150\" />\n" +
                "            <plaintext></plaintext>\n" +
                "        </subpod>\n" +
                "    </pod>\n" +
                "    <pod title=\"Leadership positions\" scanner=\"Data\" id=\"Position:LeaderData\" position=\"400\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74261c8a8ca4cgb8353000005ggcgg631171d6bi?MSPStoreType=image/gif&amp;s=61\" alt=\"official position | Prime Minister | Prime Minister country | India | India start date | 16/05/1996 | 19/03/1998 end date | 01/06/1996 | 22/05/2004\" title=\"official position | Prime Minister | Prime Minister country | India | India start date | 16/05/1996 | 19/03/1998 end date | 01/06/1996 | 22/05/2004\" width=\"364\" height=\"132\" />\n" +
                "            <plaintext>official position | Prime Minister | Prime Minister\n" +
                "                country | India | India\n" +
                "                start date | 16/05/1996 | 19/03/1998\n" +
                "                end date | 01/06/1996 | 22/05/2004</plaintext>\n" +
                "        </subpod>\n" +
                "    </pod>\n" +
                "    <pod title=\"Timeline\" scanner=\"Data\" id=\"Timeline:PeopleData\" position=\"500\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74271c8a8ca4cgb8353000005fh4e28420a8df76?MSPStoreType=image/gif&amp;s=61\" alt=\"\" title=\"\" width=\"550\" height=\"38\" />\n" +
                "            <plaintext></plaintext>\n" +
                "        </subpod>\n" +
                "    </pod>\n" +
                "    <pod title=\"Notable facts\" scanner=\"Data\" id=\"NotableFacts:PeopleData\" position=\"600\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74281c8a8ca4cgb8353000006270f4ec260i8ih2?MSPStoreType=image/gif&amp;s=61\" alt=\"Statesman and politician whose long parliamentary career included three terms as the Prime Minister of India Spent more than 40 years in parliament, and was elected a record nine times to the Lok Sabha Served 13 days as Prime Minister in 1996, then led a coalition government for six years beginning in 1998 Retired from active politics and as the Member of Parliament for Lucknow in 2009\" title=\"Statesman and politician whose long parliamentary career included three terms as the Prime Minister of India Spent more than 40 years in parliament, and was elected a record nine times to the Lok Sabha Served 13 days as Prime Minister in 1996, then led a coalition government for six years beginning in 1998 Retired from active politics and as the Member of Parliament for Lucknow in 2009\" width=\"540\" height=\"163\" />\n" +
                "            <plaintext>Statesman and politician whose long parliamentary career included three terms as the Prime Minister of India\n" +
                "                Spent more than 40 years in parliament, and was elected a record nine times to the Lok Sabha\n" +
                "                Served 13 days as Prime Minister in 1996, then led a coalition government for six years beginning in 1998\n" +
                "                Retired from active politics and as the Member of Parliament for Lucknow in 2009</plaintext>\n" +
                "        </subpod>\n" +
                "    </pod>\n" +
                "    <pod title=\"Familial relationships\" scanner=\"Data\" id=\"FamilialRelationships:PeopleData\" position=\"700\" error=\"false\" numsubpods=\"2\">\n" +
                "        <subpod title=\"Parents\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74291c8a8ca4cgb8353000003idc7i0ic8i5hach?MSPStoreType=image/gif&amp;s=61\" alt=\"Krishna Devi  |  Krishna Bihari Vajpayee\" title=\"Krishna Devi  |  Krishna Bihari Vajpayee\" width=\"263\" height=\"18\" />\n" +
                "            <plaintext>Krishna Devi  |  Krishna Bihari Vajpayee</plaintext>\n" +
                "        </subpod>\n" +
                "        <subpod title=\"Sibling\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74301c8a8ca4cgb8353000004i8ga24ge185fb50?MSPStoreType=image/gif&amp;s=61\" alt=\"Prem\" title=\"Prem\" width=\"34\" height=\"18\" />\n" +
                "            <plaintext>Prem</plaintext>\n" +
                "        </subpod>\n" +
                "    </pod>\n" +
                "    <pod title=\"Wikipedia summary\" scanner=\"Data\" id=\"WikipediaSummary:PeopleData\" position=\"800\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74311c8a8ca4cgb8353000001d29g5b6g8f732ci?MSPStoreType=image/gif&amp;s=61\" alt=\"\" title=\"\" width=\"528\" height=\"77\" />\n" +
                "            <plaintext></plaintext>\n" +
                "        </subpod>\n" +
                "        <infos count=\"1\">\n" +
                "            <info>\n" +
                "                <link url=\"http://en.wikipedia.org/wiki?curid=101730\" text=\"Full entry\" />\n" +
                "            </info>\n" +
                "        </infos>\n" +
                "    </pod>\n" +
                "    <pod title=\"Wikipedia page hits history\" scanner=\"Data\" id=\"PopularityPod:WikipediaStatsData\" position=\"900\" error=\"false\" numsubpods=\"1\">\n" +
                "        <subpod title=\"\">\n" +
                "            <img src=\"http://www4b.wolframalpha.com/Calculate/MSP/MSP74321c8a8ca4cgb8353000003266hhh7692998ed?MSPStoreType=image/gif&amp;s=61\" alt=\"\" title=\"\" width=\"546\" height=\"165\" />\n" +
                "            <plaintext></plaintext>\n" +
                "        </subpod>\n" +
                "        <states count=\"1\">\n" +
                "            <state name=\"Log scale\" input=\"PopularityPod:WikipediaStatsData__Log scale\" />\n" +
                "        </states>\n" +
                "    </pod>\n" +
                "    <sources count=\"2\">\n" +
                "        <source url=\"http://www.wolframalpha.com/sources/LeaderDataSourceInformationNotes.html\" text=\"Leader data\" />\n" +
                "        <source url=\"http://www.wolframalpha.com/sources/PeopleDataSourceInformationNotes.html\" text=\"People data\" />\n" +
                "    </sources>\n" +
                "</queryresult>";
        System.out.println(buildResponseFromXML(xml));
    }

    public static String buildFailureResponse (String query) {
        return "Sorry your query could not be resolved. Your Query was : \n" + query ;
    }
    @Test
    public void test() {
        String str = " \n";
        String s1 = str.trim();
        System.out.println(s1.length());
    }
    @Test
    public void fullTester () {
        System.out.println(getResponseForQuery("Who is Shah Rukh Khan"));
    }
}
