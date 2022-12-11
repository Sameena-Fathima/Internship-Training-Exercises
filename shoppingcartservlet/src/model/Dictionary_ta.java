package model;

import java.util.ListResourceBundle;

public class Dictionary_ta extends ListResourceBundle{
	Object obj[][]= {
			{"username","உன் பெயர்" },
			{"password","கடவுச் சொல்" },
	};
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}
