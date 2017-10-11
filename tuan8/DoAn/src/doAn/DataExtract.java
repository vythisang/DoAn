package doAn;

public class DataExtract {

	private int stt;
	private String dataExtract;
	private String date;
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	public String getDataExtract() {
		return dataExtract;
	}
	public void setDataExtract(String dataExtract) {
		this.dataExtract = dataExtract;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public DataExtract(int stt, String dataExtract, String date) {
		super();
		this.stt = stt;
		this.dataExtract = dataExtract;
		this.date = date;
	}
	public DataExtract() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DataExtract [stt=" + stt + ", dataExtract=" + dataExtract + ", date=" + date + "]";
	}
	
	
	
}
