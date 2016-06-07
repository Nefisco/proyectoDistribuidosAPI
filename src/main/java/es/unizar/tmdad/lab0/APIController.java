package es.unizar.tmdad.lab0;

import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        return getTxtFromUrl(servidor+"/books/"+id);
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

    private String getTxtFromUrl(String urlString) {
        System.out.println("Fetching content from:" + urlString);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(urlString);
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

}