package com.beidou.ybz.accountbook.widget.StickyRecyclerHeader;


import com.beidou.ybz.accountbook.mvp.entity.CurrencyModel;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<CurrencyModel.BodyBean.CurrencyListBean> {

	public int compare(CurrencyModel.BodyBean.CurrencyListBean o1, CurrencyModel.BodyBean.CurrencyListBean o2) {
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
