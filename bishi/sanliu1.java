/*如果字符串长度小于10，且是大小写字母，那么就合法，字符串有可能出现大小写字母，下划线，或者数字*/
package bishi;
import java.util.*;
public class sanliu0 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int num = input.nextInt();
			input.nextLine();
			int count = 0;
			for(int i = 0; i < num; i++) {
				String str = input.nextLine();
				if(str.length() > 10) {
					continue;
				}
				boolean valid = true;
				for(int j = 0; j < str.length(); j++) {
					if(str.charAt(j) == '_' || (str.charAt(j) >= '0' && str.charAt(j) <= '9')) {
						valid = false;
						continue;
					}
					
				}
				if(valid == true) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}
