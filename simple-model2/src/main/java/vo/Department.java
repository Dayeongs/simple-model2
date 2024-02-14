package vo;

public class Department {

	private int id;
	private String name;
	private String tel;
	private String fax;

	public Department() {}
	
	// 마우스 우클릭 -> Source -> Generate Constructor using Fields..
	public Department(int id, String name, String tel, String fax) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.fax = fax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
