package remotejenamodel;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class RemoteJenaMain {
	
	private final static String SERVER_URL = "http://localhost:8089/parliament/sparql";

	public static void main(String[] args) {

		String testQueryString = "PREFIX geo: <http://www.opengis.net/ont/geosparql#>\n"
				+ "PREFIX geof: <http://www.opengis.net/def/function/geosparql/>\n" + "SELECT ?fWKT \n" + "WHERE{\n"
				+ "?loc geo:hasGeometry ?geometry .\n" + "?geometry geo:asWKT ?fWKT .\n" + "}";
		issueSelectQuery(testQueryString);

	}

	static void issueSelectQuery(String query) {
		QueryExecution exec = QueryExecutionFactory.sparqlService(SERVER_URL, query);
		ResultSet rs = exec.execSelect();
		
		// print out all results in a nice table
		//ResultSetFormatter.out(rs);
		
		// print out results individually
		while(rs.hasNext()) {
		    QuerySolution qs = rs.next() ;
		    System.out.println(qs.getLiteral("fWKT").toString());
		}
		exec.close() ;

		

	}

}
