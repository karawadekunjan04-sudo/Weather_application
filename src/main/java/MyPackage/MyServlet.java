package MyPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;




/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

request.getRequestDispatcher("index.jsp")
  .forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
      //System.out.println("Servlet Called");
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String apiKey = "08e6c6abf72e5db17e561a761f01b6bc";

        String city = request.getParameter("city");

        if (city == null || city.trim().isEmpty()) {
            request.setAttribute("error", "Please enter a city name");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        String encodedCity = URLEncoder.encode(city, "UTF-8");

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="
                + encodedCity
                + "&units=metric"
                + "&appid="
                + apiKey;

        System.out.println("API URL = " + apiUrl);

        try {

            URL url = new URL(apiUrl);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                request.setAttribute("error", "City not found!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            StringBuilder responseContent = new StringBuilder();

            Scanner sc = new Scanner(reader);

            while (sc.hasNext()) {
                responseContent.append(sc.nextLine());
            }

            sc.close();

            System.out.println(responseContent.toString());

            Gson gson = new Gson();
            JsonObject jsonObject =
                    gson.fromJson(responseContent.toString(), JsonObject.class);

            // Date & Time
            long dateTimestamp =
                    jsonObject.get("dt").getAsLong() * 1000;

            String date = new Date(dateTimestamp).toString();

            // Temperature
            double temperature =
                    jsonObject.getAsJsonObject("main")
                              .get("temp")
                              .getAsDouble();

            // Humidity
            int humidity =
                    jsonObject.getAsJsonObject("main")
                              .get("humidity")
                              .getAsInt();

            // Wind Speed
            double windSpeed =
                    jsonObject.getAsJsonObject("wind")
                              .get("speed")
                              .getAsDouble();

            // Weather Condition
            String weatherCondition =
                    jsonObject.getAsJsonArray("weather")
                              .get(0)
                              .getAsJsonObject()
                              .get("main")
                              .getAsString();

            request.setAttribute("date", date);
            request.setAttribute("city", city);
            request.setAttribute("temperature", temperature);
            request.setAttribute("weatherCondition", weatherCondition);
            request.setAttribute("humidity", humidity);
            request.setAttribute("windSpeed", windSpeed);

            connection.disconnect();

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "Unable to fetch weather data. " + e.getMessage());
        }

        request.getRequestDispatcher("index.jsp")
               .forward(request, response);
    }
}