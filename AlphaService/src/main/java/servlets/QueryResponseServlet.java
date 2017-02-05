package servlets;

import alpha.QueryResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alankrit on 03-Feb-17.
 */
public class QueryResponseServlet extends HttpServlet {
    public void doGet (HttpServletRequest request, HttpServletResponse  response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().print(QueryResponseBuilder.getResponseForQuery(request.getParameter("query")));
    }
}
