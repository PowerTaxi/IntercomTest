import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.lang.Math;
import java.io.FileReader;

public class CustomerRecords{
    public static double distanceCalc(double x1, double y1, double x2, double y2) {
      double radius = 6378.1;

      //converts 2 points (x1,y1) and (x2,y2) to radians
      x1 = Math.toRadians(x1);
      x2 = Math.toRadians(x2);
      y1 = Math.toRadians(y1);
      y2 = Math.toRadians(y2);

      //gets the distance between the 2 points
      double angle = Math.pow(Math.sin((x2-x1) / 2),2) + Math.pow(Math.sin((y2-y1) / 2),2) * Math.cos(x1) * Math.cos(x2);
      double sum = 2 * Math.asin(Math.sqrt(angle));
      return radius * sum;
    }
  public static void main(String[] args)
  {
      //company coords
      double intercomLat = 53.339428;
      double intercomLong = -6.257664;
      JSONParser parser = new JSONParser();
      try{
          JSONArray jarray =(JSONArray) parser.parse(new FileReader("src/customers.json"));
          for(Object obj : jarray)
          {
            //reads in each entry from the json file
            JSONObject jobj = (JSONObject) obj;
            String name = (String) jobj.get("name");
            Long uid = (Long) jobj.get("user_id");
            String lat = (String) jobj.get("latitude");
            double lat2 = Double.parseDouble(lat);
            String longitude = (String) jobj.get("longitude");
            double longitude2 = Double.parseDouble(longitude);

            //only take the entries where the distance is less than 100
            if (distanceCalc(intercomLat, intercomLong, lat2, longitude2) < 100)
            {
              System.out.println("Name: " +name + " User id: " + uid);
            }
          }
      }
      catch(Exception e)
      { e.printStackTrace();}
  }
}
