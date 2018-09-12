package tools;

import javax.servlet.http.HttpServletRequest;

public class Tool {
	public static PageInformation getPageInformation(String tableName,HttpServletRequest request,PageInformation pageInformation){
		pageInformation.setTableName(tableName);
		
		String param=request.getParameter("pageSize");
		if(param==null||param.isEmpty()){
			pageInformation.setPageSize(null);
		}else{
			pageInformation.setPageSize(Integer.parseInt(param));
		}
		
		param=request.getParameter("page");
		if(param==null||param.isEmpty()){
			pageInformation.setPage(null);
		}else{
			pageInformation.setPage(Integer.parseInt(param));
		}
		
		param=request.getParameter("totalPageCount");
		if(param==null||param.isEmpty()){
			pageInformation.setTotalPageCount(null);
		}else{
			pageInformation.setTotalPageCount(Integer.parseInt(param));
		}
		
		param=request.getParameter("allRecordCount");
		if(param==null||param.isEmpty()){
			pageInformation.setAllRecordCount(null);
		}else{
			pageInformation.setAllRecordCount(Integer.parseInt(param));
		}
		pageInformation.setIds(request.getParameter("ids"));
		pageInformation.setOrder(request.getParameter("order"));
		pageInformation.setOrderField(request.getParameter("orderField"));
		pageInformation.setSearchSql(request.getParameter("searchSql"));
		return pageInformation;
	}
	public static String getSql(PageInformation pageInformation,String type){
		String sql="";
		if(pageInformation.getIds()!=null&&!pageInformation.getIds().isEmpty()){
			sql+=""+" delete * form"+pageInformation.getTableName().toLowerCase()
					+" where "+pageInformation.getTableName().toLowerCase()+"Id in ("
					+" "+pageInformation.getIds()+") ";
		}else if("count".equals(type)){
			sql+=""+" select count(*) as count1 from "+pageInformation.getTableName().toLowerCase()+" ";
			if(pageInformation.getSearchSql()!=null&&!pageInformation.getSearchSql().isEmpty()){
				sql+=" where"+pageInformation.getSearchSql()+" ";
			}
		}else if("select".equals(type)){
			sql+=""+" select * from "+pageInformation.getTableName().toLowerCase()+" ";
			if(pageInformation.getSearchSql()!=null&&!pageInformation.getSearchSql().isEmpty()){
				sql+=" where"+pageInformation.getSearchSql()+" ";
			}
			
			if(pageInformation.getOrderField()==null||pageInformation.getOrderField().isEmpty()){
				sql+=" order by "+pageInformation.getTableName()+"Id desc ";
			}else{
				sql+=" order by "+pageInformation.getOrderField()+" "+pageInformation.getOrder()+" ";
			}
			
			if(pageInformation.getPage()!=null&&pageInformation.getPage()>-1){
				Integer start=(pageInformation.getPage()-1)*pageInformation.getPageSize();
				sql+=" limit "+start.toString()+","+pageInformation.getPageSize();
			}
		}
		return sql;
	}
	public static void setPageInformation(Integer allRecordCount,PageInformation pageInformation){
		pageInformation.setAllRecordCount(allRecordCount);
		Integer totalPageCount=(int) Math.ceil(1.0*allRecordCount/pageInformation.getPageSize());
		pageInformation.setTotalPageCount(totalPageCount);
		if(pageInformation.getPage()==null) return;
		if(pageInformation.getPage()<1){
			pageInformation.setPage(1);
		}
		if(pageInformation.getPage()>totalPageCount&&totalPageCount>0){
			pageInformation.setPage(totalPageCount);
		}
		
	}
}
