package com.example.app.model;

import java.util.Objects;

public class Post {
	private String name;
	private String message;
	
	public Post(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Post [name=" + name + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(message, other.message) && Objects.equals(name, other.name);
	}
	
}
