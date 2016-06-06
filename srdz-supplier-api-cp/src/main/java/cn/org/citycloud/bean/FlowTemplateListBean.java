package cn.org.citycloud.bean;

import java.util.ArrayList;
import java.util.List;

public class FlowTemplateListBean {
	
	FlowTemplateBean flowTemplate = new FlowTemplateBean();

	FlowInfoBeanCopy flowInfo = new FlowInfoBeanCopy();
	
	List<FlowInfoBean> flowInfolist = new ArrayList<FlowInfoBean>();
	

	public FlowInfoBeanCopy getFlowInfo() {
		return flowInfo;
	}

	public void setFlowInfo(FlowInfoBeanCopy flowInfo) {
		this.flowInfo = flowInfo;
	}

	public List<FlowInfoBean> getFlowInfolist() {
		return flowInfolist;
	}

	public void setFlowInfolist(List<FlowInfoBean> flowInfolist) {
		this.flowInfolist = flowInfolist;
	}

	public FlowTemplateBean getFlowTemplate() {
		return flowTemplate;
	}

	public void setFlowTemplate(FlowTemplateBean flowTemplate) {
		this.flowTemplate = flowTemplate;
	}

	
	
	
	
}
