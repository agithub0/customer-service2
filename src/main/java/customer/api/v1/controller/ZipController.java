package customer.api.v1.controller;

import com.fasterxml.jackson.annotation.JsonView;
import customer.api.v1.model.View;
import customer.api.v1.model.ZipCode;
import customer.api.v1.service.ZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ZipController {

    @Autowired
    ZipService zipService;

    @GetMapping("/zips")
    @JsonView(View.zipView.class)
    public List<ZipCode> getALl(){
        return zipService.getAll();
    }

    @GetMapping("/zips/{id}")
    @JsonView(View.zipView.class)
    public ZipCode getById(@PathVariable("id") int id){
        return zipService.getById(id);
    }

    @PutMapping("/zips/{id}")
    @JsonView(View.zipView.class)
    public ZipCode updateById(@PathVariable("id") int id,@Valid @RequestBody ZipCode zipCode1){
        return zipService.updateById(id,zipCode1);
    }

    @PostMapping("/zip")
    @JsonView(View.zipView.class)
    public ZipCode createZipCode(@Valid @RequestBody ZipCode zipCode1){
        return zipService.createZipCode(zipCode1);
    }

    @DeleteMapping("/zip/{id}")
    @JsonView(View.zipView.class)
    public void delete(@PathVariable("id") int id){
        zipService.deleteZipCode(id);
    }


}
