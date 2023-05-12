package edu.ssafy.enjoytrip.util;

import org.springframework.stereotype.Component;

@Component
public class PageNavigationHandler {
	
	public PageNavigation makePageNavigation(int pageNo, int totalCount) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;

		pageNavigation.setCurrentPage(pageNo);
		pageNavigation.setNaviSize(naviSize);
		
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = pageNo <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < pageNo;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}
}
