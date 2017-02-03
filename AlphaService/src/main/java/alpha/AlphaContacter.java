package alpha;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Alankrit on 03-Feb-17.
 */
public class AlphaContacter {
    public static final String wolframAlphaKey = "5KWAUR-66U453RWXH";
    public static String fetchResult (String query) throws Exception {
        String urlString = "http://api.wolframalpha.com/v2/query?input=" + URLEncoder.encode(query) + "&appid=" + wolframAlphaKey;
        URL url = new URL(urlString) ;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        String xmlResponse = IOUtils.toString(httpURLConnection.getInputStream());
        return xmlResponse;
    }

    @Test
    public void xmresponderTest () throws Exception {
        System.out.println(fetchResult("Who is Atal Bihari Vajpayee"));
    }

}
