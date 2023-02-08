package org.greatlearning.empmgmt_rest_api.utility;

import javax.transaction.Transactional;

import org.greatlearning.empmgmt_rest_api.entity.Employee;
import org.greatlearning.empmgmt_rest_api.entity.Role;
import org.greatlearning.empmgmt_rest_api.entity.User;
import org.greatlearning.empmgmt_rest_api.repo.EmployeeRepo;
import org.greatlearning.empmgmt_rest_api.repo.RoleRepo;
import org.greatlearning.empmgmt_rest_api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BootstrapAppData {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private RoleRepo roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public BootstrapAppData(EmployeeRepo employeeRepo, UserRepo userRepository, PasswordEncoder passwordEncoder) {
		this.employeeRepo = employeeRepo;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	//Dummy employees
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void usersData(ApplicationReadyEvent readyEvent) {

		Employee shahid = new Employee("Mr", "shahid", "shahid@gmail.com");
		Employee rupali = new Employee("Rupali", "Singh", "rupali@gmail.com");
		Employee seema = new Employee("Seema", "Soni", "seema@gmail.com");
		Employee sanju = new Employee("Sanju", "Punfer", "punfer@gmail.com");
		Employee neha = new Employee("Neha", "Rohilla", "neha@gmail.com");
	

		this.employeeRepo.save(shahid);
		this.employeeRepo.save(rupali);
		this.employeeRepo.save(seema);
		this.employeeRepo.save(sanju);
		this.employeeRepo.save(neha);
	}

	//Dummy users
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertAllData(ApplicationReadyEvent event) {
		User shahid = new User("shahid", passwordEncoder.encode("shahid"));
		User admin = new User("admin", passwordEncoder.encode("admin"));


		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");

		roleRepository.saveAndFlush(userRole);
		roleRepository.saveAndFlush(adminRole);

		admin.addRole(adminRole);
		shahid.addRole(userRole);

		userRepository.saveAndFlush(shahid);
		userRepository.saveAndFlush(admin);

	}

}
