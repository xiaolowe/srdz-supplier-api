package cn.org.citycloud.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class TodoBean {

	@NotEmpty
	@Length(max = 255)
	private String todo;

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}
	
	
}
