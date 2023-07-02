package web.controller;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import web.model.Student;
import web.jpaRepo.*;

@Controller
@Slf4j
@RequestMapping("/danhsach")
public class StudentController {
	private final JpaStudentRepository studentRepo;
	@Autowired
	public StudentController(JpaStudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}
	@GetMapping("/view")
	public String danhSachSV(Model module) {
		List<Student> students =new ArrayList<>();
		studentRepo.findAll().forEach(students::add);
		module.addAttribute("students", students);
		return "danhsach";
	}
	@PostMapping("/approve/{id}")
	public String approved(@Valid @PathVariable String id) {
		Optional<Student> studentOptional = studentRepo.findById(id);
		if(studentOptional.isPresent()) {
			Student student = studentOptional.get();
			student.setApproved(1);
			studentRepo.save(student);
		}
		return "redirect:/danhsach/view";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@Valid @PathVariable String id, Model model) {
		Optional<Student> studentOptional = studentRepo.findById(id);
		if(studentOptional.isPresent()) {
			Student student = studentOptional.get();
			model.addAttribute("student", student);
		}
		return "confirm";
	}
	
	@PostMapping("/confirm/{id}")
	public String confirm(@Valid @PathVariable String id) {
		studentRepo.deleteById(id);
		return "redirect:/danhsach/view";
	}
}
