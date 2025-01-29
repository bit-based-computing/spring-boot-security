package method.level.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String test(){

        return testService.getMessage();
    }

    @GetMapping("/profession/{name}")
    public List<String> getProfession(@PathVariable String name){
        return testService.getProfession(name);
    }

    @GetMapping("/profession2/{name}")
    public List<String> getProfession2(@PathVariable String name){
        return testService.getProfession2(name);
    }
}
