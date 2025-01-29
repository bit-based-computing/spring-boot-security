package method.level.authorization;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    private Map<String, List<String>> secretNames =
            Map.of("user", List.of("Teacher", "Doctor"),
                    "user2", List.of("Engineer"));

    @PreAuthorize("hasAnyAuthority('write')")
    String getMessage(){
        return "test";
    }

    // use method variable in PreAuthorize method and authentication principle
    @PreAuthorize("#name == authentication.principal.username ")
    List<String> getProfession(String name){
        return secretNames.get(name);
    }


    @PostAuthorize("returnObject.contains('Teacher')")
    List<String> getProfession2(String name){
        return secretNames.get(name);
    }


}
