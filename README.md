# iOSUtils v1.0
Scrapes https://www.theiphonewiki.com/ for Keys and IV's for a specific firmware on a device (if keys and ivs are present)

## Usage

```java
Utils.getKeyFor("iPad2,5", "8.4.1", KeyTypes.KERNELCACHE).getIv();
Utils.getKeyFor("iPad2,5", "8.4.1", KeyTypes.KERNELCACHE).getKey();
