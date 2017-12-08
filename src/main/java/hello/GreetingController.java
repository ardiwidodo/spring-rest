package hello;


import static org.assertj.core.api.Assertions.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.sampled.Line;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}

