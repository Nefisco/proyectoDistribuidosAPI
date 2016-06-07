package es.unizar.tmdad.lab0;


import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@RestController
public class APIController {

    String servidor= "https://books-analyzer.herokuapp.com/";


    @CrossOrigin
    @RequestMapping(value = "/books/{id}", method = { RequestMethod.GET })
    public String getBook(@PathVariable("id") Integer id )  {
        return getTxtFromUrl(servidor+"/books/"+id);
    }
    @CrossOrigin
    @RequestMapping(value = "/books/{id}", method = { RequestMethod.PUT })
    public String editBook(
            @PathVariable("id") Integer id,
            @RequestBody(required=false) String title,
            @RequestBody(required=false) String author
    )  {
        return getTxtFromUrl(servidor+"/books/"+id);
    }
    @CrossOrigin
    @RequestMapping(value = "/books/{id}", method = { RequestMethod.DELETE })
    public String deleteBook(@PathVariable("id") Integer id)  {

        return sendGET(servidor+"/books/"+id);
    }


    @CrossOrigin
    @RequestMapping(value = "/books", method = { RequestMethod.POST })
    public String createBook (
            @RequestParam(value = "title", required=false) String title,
            @RequestParam(value = "author", required=false) String author,
            @RequestParam(value = "url", required=false) String url
    )  {
      //  return "forward:/"+servidor+"/books/"+id);
        return getTxtFromUrl(servidor+"/books/");
    }

    //INTEGRAR JWT

    private String getTxtFromUrl(String urlString) {

        //intregrr VERBO put post ...
        System.out.println("Fetching content from:" + urlString);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(urlString);
            //configurar url o url conection como configurar peticion post put XX XX
            urlConn = url.openConnection();
            urlConn.setRequestProperty("User-Agent", "Chrome/23.0.1271.95");


            if (urlConn != null)
                urlConn.setReadTimeout(60000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("A problem occured while calling URL:"+ urlString, e);
        }

        return sb.toString();
    }

    private String sendGET(String url) {
        try {
            String respuesta = "";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Chrome/23.0.1271.95");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());

                respuesta = response.toString();
            } else {
                System.out.println("GET request not worked");
                respuesta = "fail";
            }
            return respuesta;
        } catch (Exception e) {
            System.out.println("FAIL");
            e.printStackTrace();
            return "FAIL";
        }
    }


        private static void sendPOST() throws IOException {
            URL obj = new URL(POST_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        }


    }



