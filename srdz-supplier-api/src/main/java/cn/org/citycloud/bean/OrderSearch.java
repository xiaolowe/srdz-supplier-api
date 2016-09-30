package cn.org.citycloud.bean;

/**
 * desc the file.
 *
 * @author demon
 * @Date 2016/4/25 10:38
 */
public class OrderSearch extends Page {

    private String stime;

    private String etime;

    private int orderStatus = -1;

    private String memberName;

    private String orderId;

    private String supplierName;

    private int backOrderStatus = -1;
    
    private int supplierId;

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }


    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getBackOrderStatus() {
        return backOrderStatus;
    }

    public void setBackOrderStatus(int backOrderStatus) {
        this.backOrderStatus = backOrderStatus;
    }

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
    
    
}
