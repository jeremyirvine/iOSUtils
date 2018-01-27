package iOSUtils;

public enum KeyTypes {
	ROOTFS("keypage-rootfs"),
	UPDATE_RD("keypage-updateramdisk"),
	RESTORE_RD("keypage-restoreramdisk"),
	APPLE_LOGO("keypage-applelogo"),
	BATTERYCHARGING0("keypage-batterycharging0"),
	BATTERYCHARGING1("keypage-batterycharging1"),
	BATTERYFULL("keypage-batteryfull"),
	BATTERYLOW0("keypage-batterylow0"),
	BATTERYLOW1("keypage-batterylow1"),
	DEVICETREE("keypage-devicetree"),
	GLYPHPLUGIN("keypage-glyphplugin"),
	IBEC("keypage-ibec"),
	IBOOT("keypage-iboot"),
	IBSS("keypage-ibss"),
	KERNELCACHE("keypage-kernelcache"),
	LLB("keypage-llb"),
	RECOVERYMODE("keypage-recoverymode");
	
		private String value;    

	  private KeyTypes(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return value;
	  }
}
