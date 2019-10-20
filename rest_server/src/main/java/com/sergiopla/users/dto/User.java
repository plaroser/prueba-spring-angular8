package com.sergiopla.users.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Comparable<User> {
	final String PATTERN_PASSWORD = "^(?=.*[a-zA-Z])(?=.*[0-9])";
	final int MIN_PASSWORD_LENGTH = 4;
	final String PATTERN_PHONE_NUMBER = "[0-9]{9}";
	final String PATTERN_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	final String PATTERN_NUMBER = "[0-9]+";

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String age;
	private boolean active;

	private MessageDigest messageDigest;

	public User() {
		super();
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	public User(String username, String password, String firstName, String lastName, String phoneNumber, String email,
			String age, boolean active) throws Exception {
		this();
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setEmail(email);
		setAge(age);
		setActive(active);
	}

	public User(String username, String password) throws Exception {
		this(username, password, null, null, "000000000", null, "1", false);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws Exception {
		if (username.length() <= 0)
			throw new Exception("La logitud del nombre de usuario no es correcta");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		System.out.println("setPassword " + password);
		if (password.length() < MIN_PASSWORD_LENGTH)
			throw new Exception("La contraseña tiene que tener una longitud minima de 5 caracteres");
		/*
		 * else if (!password.matches(PATTERN_PASSWORD)) throw new
		 * Exception("La contraseña tiene que contener al menos un número y una letra");
		 */

		this.password = getStringFromSHA256(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws Exception {
		if (!phoneNumber.matches(PATTERN_PHONE_NUMBER))
			throw new Exception("El número tiene que contener 6 digitos numéricos");
		this.phoneNumber = phoneNumber;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if (!email.matches(PATTERN_EMAIL))
			throw new Exception("El email no es correcto");
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) throws Exception {
		if (!age.matches(PATTERN_NUMBER))
			throw new Exception("Edad incorrecta");
		this.age = age;
	}

	private String getStringFromSHA256(String stringToEncrypt) {
		messageDigest.update(stringToEncrypt.getBytes());
		return new String(messageDigest.digest());
	}

	public int compareTo(User other) {
		System.out.println("compareTo " + this.username.compareTo(other.username));
		return this.username.compareTo(other.username);
	}

	@Override
	public boolean equals(Object o) {
		User other = (User) o;
		return this.username.equals(other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
}
