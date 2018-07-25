package com.bridgelabz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.toDoApplication.ToDoApplication;
import com.bridgelabz.toDoApplication.userservice.controller.UserController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ToDoApplication .class)
@SpringBootTest
public class ToDoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
