package com.beidou.ybz.accountbook.widget.StickyRecyclerHeader;


import com.beidou.ybz.accountbook.mvp.entity.ContactsModel;
import com.beidou.ybz.accountbook.mvp.entity.CurrencyModel;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparatorContacts implements Comparator<ContactsModel> {

	public int compare(ContactsModel o1, ContactsModel o2) {
		if (o1.getInitials().equals("@")
				|| o2.getInitials().equals("#")) {
			return -1;
		} else if (o1.getInitials().equals("#")
				|| o2.getInitials().equals("@")) {
			return 1;
		} else {
			return o1.getInitials().compareTo(o2.getInitials());
		}
	}

}
