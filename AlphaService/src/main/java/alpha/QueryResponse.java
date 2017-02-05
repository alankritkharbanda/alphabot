package alpha;

import com.google.gson.Gson;
import utils.ObjectToJSON;

/**
 * Created by Alankrit on 04-Feb-17.
 */
public class QueryResponse {
    String query ;
    String response ;

    public QueryResponse(String query, String response) {
        this.query = query;
        this.response = response;
    }

    public String toString() {
        return ObjectToJSON.convert(this);
    }
}
