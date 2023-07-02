package web.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name="student")
public class Student {
	@Id
	@NotBlank
	private final String id;
	@NotBlank
	private String name;
	@NotBlank
	private LocalDate dob;
	@NotBlank
	private String department;
	@NotBlank
	private int approved;
}
