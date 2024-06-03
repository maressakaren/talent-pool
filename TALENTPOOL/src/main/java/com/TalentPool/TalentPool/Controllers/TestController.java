package com.TalentPool.TalentPool.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/example")

public class TestController {
	/*@GetMapping("/hello-world")*/
	public ResponseEntity<String> get(int x) {
        System.out.println(x);
		return ResponseEntity.ok("Hello World Maressa!");
	}
    
    
}
