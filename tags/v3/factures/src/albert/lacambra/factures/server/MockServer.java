package albert.lacambra.factures.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MockServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//	public final static String SERVICEENDPOINT = "http://192.168.10.24:8080/pp_api/rest";
	public final static String SERVICEENDPOINT = "http://localhost:8081/rest";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws MalformedURLException, IOException{
		System.out.println("mokserver:get");
		try{
			URL url = new URL(MockServer.SERVICEENDPOINT + req.getPathInfo()); 
			HttpURLConnection connection =(HttpURLConnection) url.openConnection(); 
			connection.setRequestProperty("Authorization", req.getHeader("Authorization"));
			connection.setRequestMethod("GET"); 
			this.sendResponse(connection, resp);

			connection.disconnect();

		}catch(MalformedURLException e){
			System.out.println(e.getStackTrace().toString());

		}catch(IOException e){
			System.out.println("Io exception:" + e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws MalformedURLException, IOException{

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws MalformedURLException, IOException{

		try{
			System.out.println("mokserver:put");
			char[] buffer = new char[req.getContentLength()];

			Writer writer = new StringWriter();

			int n;
			while ((n = req.getReader().read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}

			URL url = new URL(MockServer.SERVICEENDPOINT + req.getPathInfo()); 
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", req.getHeader("Authorization"));
			connection.setRequestMethod("GET"); 
			connection.setRequestMethod("PUT");

			connection.setDoOutput(true); 
			OutputStreamWriter ostream = new OutputStreamWriter(connection.getOutputStream());

			if(buffer!= null){ 
				BufferedWriter out = new BufferedWriter(ostream); 
				out.write(buffer); 
				out.flush(); 
				out.close(); 
			} 

			this.sendResponse(connection, resp);

			connection.disconnect();

		}catch(MalformedURLException e){
			System.out.println(e.getStackTrace().toString());

		}catch(IOException e){
			System.out.println("Io exception:" + e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws MalformedURLException, IOException{
		System.out.println("mokserver:del");
		try{
			URL url = new URL(MockServer.SERVICEENDPOINT); 
			HttpURLConnection connection =(HttpURLConnection) url.openConnection(); 
			//			connection.addRequestProperty(req.getAuthType(), value)
			connection.setRequestMethod("DELETE"); 
			connection.setRequestProperty("Authorization", req.getHeader("Authorization"));
			connection.setRequestMethod("GET"); 
			this.sendResponse(connection, resp);

			connection.disconnect();

		}catch(MalformedURLException e){
			System.out.println(e.getStackTrace().toString());

		}catch(IOException e){
			System.out.println("Io exception:" + e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws MalformedURLException, IOException{
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws MalformedURLException, IOException{

	}

	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws MalformedURLException, IOException{

	}

	protected String getResource(){

		return null;
	}

	protected void sendResponse(HttpURLConnection connection, HttpServletResponse resp){

		try {

			resp.setCharacterEncoding(connection.getContentEncoding());
			resp.setContentType(connection.getContentType());
			resp.setStatus(connection.getResponseCode());

			InputStream stream = connection.getInputStream(); 
			BufferedInputStream in = new BufferedInputStream(stream); 

			Writer writer = new StringWriter();
			Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			char[] buffer = new char[1024];

			try {
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			}catch(Exception e) 
			{
				in.close();
			} 

			String out = writer.toString();

			this.setHeaders(connection, resp);

			if(out.length() > 0)
			{
				resp.setContentLength(out.length());
				ServletOutputStream sos = resp.getOutputStream();
				sos.println(out);
			}

			in.close();

		} catch (IOException e) {
//			e.printStackTrace();
		} 

		try {
			resp.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setHeaders(HttpURLConnection connection, HttpServletResponse resp){

		Map<String, List<String>> headers = connection.getHeaderFields();

		for (Map.Entry<String, List<String>> entry : headers.entrySet()) {

			String value = "";
			List<String> valList = entry.getValue();
			int i = 0;

			for(String val : valList){

				if(valList.size() - 1 != i)
					value = value + val + ";"; 
				else 
					value = value + val; 

				i++;
			}

			String prop = entry.getKey() == null ? "" : entry.getKey();

			resp.setHeader(prop, value);
		}
	}
}










































































