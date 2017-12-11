package hello;


import static org.assertj.core.api.Assertions.filter;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.sampled.Line;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*web service ada dua, ada yan pake rest (ada dua get dan post )dan soap -->yang dilempar xml
 * http://www.webservicex.net/CurrencyConvertor.asmx?op=ConversionRate -> conto web service soap dan rest
--> conto ambil data dengan soap http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency=USD&ToCurrency=IDR

*/
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
        
        
    }
    @RequestMapping ("/data")
    public List<String> dataNegara(@RequestParam("pre") String prefix){
    	List<String> data= new ArrayList<>();
    	data.add("Indonesia");
    	data.add("Malaysia");
    	data.add("Brunei");
    	data.add("timor");
    	//line -> line.startsWith fitur lamda expression  -> hanya ada di java 8
    return data.stream().filter(line -> line.contains(prefix)).collect(Collectors.toList());
    //dipanggil dengan http://localhost:8080/data?pre=M
    }
    @RequestMapping ("/countries")
    public String getCountries() throws IOException {
    	URL url = new URL("http://www.webservicex.net/country.asmx/GetCountries"); //ada di http://webservicex.net/New/Home/ServiceDetail/17
    	URLConnection connection = url.openConnection();
    	// objek connection ini untuk post --> tidak bisa langsung dicoba -> bikin form dulu dengan method post
    	connection.setDoOutput(true);
    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	connection.setRequestProperty("Content-Length", "0");
    	
    	
    	InputStream stream = url.openConnection().getInputStream();
    
    	InputStreamReader reader = new InputStreamReader(stream);
    	BufferedReader buffer = new BufferedReader(reader);
    	String line;
    	StringBuilder builder = new StringBuilder();
    	while ((line =buffer.readLine())!=null) { builder.append(line);
			
		}
    	return builder.toString();
    	//return new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
    }
    
    	
    	
    }


