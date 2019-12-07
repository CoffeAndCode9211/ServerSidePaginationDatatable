package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public UsersDTO getCurrentUser(@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("draw") int draw,
			@RequestParam("order[0][column]") int sortColIndex,
			@RequestParam("order[0][dir]") String order,
			@RequestParam("columns[0][data]") String col0DataAttrName) {
		
		// To handle Sorting
		// sortColIndex => which column index is being sorted
		// order => asc or desc
		// col0DataAttrName => can be used to pass column name dymanically in DB Query

		
		int totalUsers = userRepository.totalUsers();
		UsersDTO usersDto = new UsersDTO();
		usersDto.setData(userRepository.findUserWithPage(start, length));
		usersDto.setRecordsFiltered(totalUsers);
		usersDto.setRecordsTotal(totalUsers); // Needed to show Pagination in Datatable
		usersDto.setDraw(draw);
		return usersDto;
	}
	
	
}
