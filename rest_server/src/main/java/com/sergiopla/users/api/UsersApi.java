package com.sergiopla.users.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sergiopla.users.dao.UserDatabase;
import com.sergiopla.users.dto.Response;
import com.sergiopla.users.dto.User;

@RestController
public class UsersApi {

	private UserDatabase db = new UserDatabase();

	@RequestMapping(value = "/user/{username}")
	@ResponseBody
	public Response getbyUsername(@PathVariable("username") String username) {
		User user = null;
		Response response = new Response(false, "", user);
		try {
			user = db.findByUsername(username);
			response.setObject(user);
		} catch (Exception e) {
			response.setHaveError(true);
			response.setErrorMsn(e.getMessage());
		}
		System.out.println("response " + response);
		return response;
	}

	@GetMapping("/users")
	public Response findAll() {
		List<User> users = null;
		Response response = new Response(false, "", users);
		try {
			users = db.findAll();
			response.setObject(users);
		} catch (Exception e) {
			response.setHaveError(true);
			response.setErrorMsn(e.getMessage());
		}
		System.out.println("findAll response " + response);
		return response;
	}

	@GetMapping("/users/{username}")
	public Response getUser(@PathVariable String username) {
		User user = null;
		Response response = new Response(false, "", user);
		try {
			user = db.findByUsername(username);
			response.setObject(user);
		} catch (Exception e) {
			response.setHaveError(true);
			response.setErrorMsn(e.getMessage());
		}
		System.out.println("response " + response);
		return response;
	}

	/*
	 * @PostMapping("/users") public Response addUser(User user) {
	 * System.out.println("addUser" + user); User userSaved = null; Response
	 * response = new Response(false, "", userSaved); try { userSaved =
	 * db.save(user); response.setObject(userSaved); } catch (Exception e) {
	 * response.setHaveError(true); response.setErrorMsn(e.getMessage()); }
	 * System.out.println("response " + response); // retornará todos los usuarios
	 * return response; }
	 */

	@PutMapping("/users")
	public Response updateUser(@RequestBody User user) {
		User userUpdated = null;
		Response response = new Response(false, "", userUpdated);
		try {
			userUpdated = db.save(user);
			response.setObject(userUpdated);
		} catch (Exception e) {
			response.setHaveError(true);
			response.setErrorMsn(e.getMessage());
		}
		System.out.println("response " + response);
		return response;
	}

	@PutMapping("/delete/{username}")
	public Response deteteUser(@PathVariable String username) {
		User userDeleted = null;

		Response response = new Response(false, "", userDeleted);
		try {
			userDeleted = db.deleteByUsername(username);
			response.setObject(userDeleted);
		} catch (Exception e) {
			response.setHaveError(true);
			response.setErrorMsn(e.getMessage());
		}
		System.out.println("deteteUser response " + response);
		return response;
	}
}
