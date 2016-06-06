package cn.org.citycloud.bean;


import javax.validation.constraints.Min;


import io.swagger.annotations.ApiModelProperty;

public class FlowTemplateBean {

	@ApiModelProperty(value = "物流模板ID")
	private int flowTemplateId;
	
	@ApiModelProperty(value = "物流名称")
	private String flowTemplateName;

	@Min(1)
	@ApiModelProperty(value="重量件数标识( 1 重量   2 件数)", required=true)
	private int weightPieceFlag;
	

	public String getFlowTemplateName() {
		return flowTemplateName;
	}


	public void setFlowTemplateName(String flowTemplateName) {
		this.flowTemplateName = flowTemplateName;
	}


	public int getWeightPieceFlag() {
		return weightPieceFlag;
	}


	public void setWeightPieceFlag(int weightPieceFlag) {
		this.weightPieceFlag = weightPieceFlag;
	}


	public int getFlowTemplateId() {
		return flowTemplateId;
	}


	public void setFlowTemplateId(int flowTemplateId) {
		this.flowTemplateId = flowTemplateId;
	}


	

	
	
	
	
	
}
