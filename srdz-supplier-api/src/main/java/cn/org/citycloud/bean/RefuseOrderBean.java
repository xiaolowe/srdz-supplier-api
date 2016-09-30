package cn.org.citycloud.bean;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="拒绝退款Model", description="拒绝退款Model")
public class RefuseOrderBean {
	
	@NotBlank(message = "退款原因不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "退款原因", required = true)
	private String orderCause;

	public String getOrderCause() {
		return orderCause;
	}

	public void setOrderCause(String orderCause) {
		this.orderCause = orderCause;
	}
	
	
	
	
	
	
}
