package cn.org.citycloud.bean.chat;

import java.util.List;

public class ChartData {
	private String name;
	
	private String type;
	
	private List<Object> data;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
