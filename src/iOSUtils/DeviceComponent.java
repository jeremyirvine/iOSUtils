package iOSUtils;

public class DeviceComponent {
	
	private String iv, key;
	
	public DeviceComponent(String iv, String key) {
		this.iv = iv;
		this.key = key;
	}
	
	public DeviceComponent(String key) {
		this.key = key;
		this.iv = "";
	}
	
	public String getIv() {
		return iv;
	}
	
	public String getKey() {
		return key;
	}
	
}
