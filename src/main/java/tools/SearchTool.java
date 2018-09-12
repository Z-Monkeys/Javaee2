package tools;

import javax.servlet.http.HttpServletRequest;

import bean.News;

public class SearchTool {
	private static String addStringFuzzyAnd(String fieldName,String searchSql,HttpServletRequest request){
		String param=request.getParameter(fieldName);
		if(param!=null&&!param.isEmpty()&&!param.equals("all")){
			if(param.equals("all")){
				System.out.println("wrong");
			}
			if(searchSql.length()>1){
				searchSql+=" and "+fieldName+" like '%"+param+"%' ";
			}else{
				searchSql+=" "+fieldName+" like '%"+param+"%' ";
			}
		}
		return searchSql;
	}
	private static String addStringPrecisionAnd(String fieldName,String searchSql,HttpServletRequest request){
		String param=request.getParameter(fieldName);
		if(param!=null&&!param.isEmpty()&&!param.equals("all")){
			if(param.equals("all")){
				System.out.println("wrong");
			}
			if(searchSql.length()>1){
				searchSql+=" and "+fieldName+"='"+param+"' ";
			}else{
				searchSql+=" "+fieldName+"='"+param+"' ";
			}
		}
		return searchSql;
	}
	private static String addDateAnd(String fieldName,String lowDateName,String upDateName,String searchSql,HttpServletRequest request){
		String lowDate=request.getParameter(lowDateName);
		String upDate=request.getParameter(upDateName);
		if(lowDate!=null&&!lowDate.isEmpty()){
			if(searchSql.length()>1){
				searchSql+=" and "+fieldName+">='"+lowDate+"' ";
			}else{
				searchSql+=" "+fieldName+">='"+lowDate+"' ";
			}
		}
		if(upDate!=null&&!upDate.isEmpty()){
			if(searchSql.length()>1){
				searchSql+=" and "+fieldName+"<'"+upDate+"' ";
			}else{
				searchSql+=" "+fieldName+"<'"+upDate+"' ";
			}
		}
		return searchSql;	
	}
	public static String user(HttpServletRequest request){
		String searchSql="";
		searchSql=addStringPrecisionAnd("type",searchSql,request);
		searchSql=addStringFuzzyAnd("name",searchSql,request);
		searchSql=addStringPrecisionAnd("enable",searchSql,request);
		searchSql=addDateAnd("registerDate","lowDate","upDate",searchSql,request);
		return searchSql;
	}
	
}
