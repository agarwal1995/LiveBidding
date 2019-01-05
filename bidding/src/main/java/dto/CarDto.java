package dto;

public class CarDto {

	private Long id;
	private String modal;
	private Long year;
	private Long price;
	private String type;
	private Long km;
	private String fuelType;
	private Long previousOwner;
	private String insuranceType;
	private String rto;
	private String imageUrl;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModal() {
		return modal;
	}
	public void setModal(String modal) {
		this.modal = modal;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getKm() {
		return km;
	}
	public void setKm(Long km) {
		this.km = km;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Long getPreviousOwner() {
		return previousOwner;
	}
	public void setPreviousOwner(Long previousOwner) {
		this.previousOwner = previousOwner;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getRto() {
		return rto;
	}
	public void setRto(String rto) {
		this.rto = rto;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
