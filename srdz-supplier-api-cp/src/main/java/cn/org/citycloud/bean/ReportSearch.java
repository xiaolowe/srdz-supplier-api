package cn.org.citycloud.bean;

/**
 * desc the file.
 *
 */
public class ReportSearch extends Page {

    private int supplierId = -1;

    private int classfyId = -1;

    private int year = -1;

    private int month = -1;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getClassfyId() {
        return classfyId;
    }

    public void setClassfyId(int classfyId) {
        this.classfyId = classfyId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
