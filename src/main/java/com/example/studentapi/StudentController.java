//imports
package com.example.studentapi;
import org.springframework.web.bind.annotation.*;
import java.util.*;

//annotations
@RestController
@RequestMapping("/students")


//classes
public class StudentController {
    private List<Map<String, Object>> students = new ArrayList<>();

    @GetMapping
public List<Map<String, Object>> getAllStudents() {
    return students;
}

@PostMapping
public String addStudent(@RequestBody Map<String, Object> student) {
    students.add(student);
    return "Student added";
}

@GetMapping("/{id}")
public Map<String, Object> getStudent(@PathVariable int id) {
    for (Map<String, Object> s : students) {
        if ((int) s.get("id") == id) {
            return s;
        }
    }
    return null;
}

@DeleteMapping("/{id}")
public String deleteStudent(@PathVariable int id) {
    students.removeIf(s -> Integer.parseInt(s.get("id").toString()) == id);
    return "Student deleted";
}

@PutMapping("/{id}")
public String updateStudent(@PathVariable int id, @RequestBody Map<String, Object> updatedStudent) {
    for (Map<String, Object> s : students) {
        if (Integer.parseInt(s.get("id").toString()) == id) {
            s.put("name", updatedStudent.get("name"));
            s.put("course", updatedStudent.get("course"));
            return "Student updated";
        }
    }
    return "Student not found";
}


}
