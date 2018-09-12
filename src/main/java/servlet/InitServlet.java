package servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import bean.Authority;
import bean.NewsType;
import dao.DatabaseDao;
import service.AuthorityService;
import service.NewsTypeService;
import tools.AuthorityTool;
import tools.FileTool;
import tools.WebProperties;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InitServlet() {
		super();

	}

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);

		DatabaseDao.databaseType = this.getServletContext().getInitParameter("databaseType");
		DatabaseDao.databaseUser = this.getServletContext().getInitParameter("databaseUser");
		DatabaseDao.databaseUrl = this.getServletContext().getInitParameter("databaseUrl");
		DatabaseDao.databasePassword = this.getServletContext().getInitParameter("databasePassword");
		
		ServletContext servletContext=conf.getServletContext();
		FileTool.root=servletContext.getRealPath("\\");
		
		String fileDir=servletContext.getRealPath("\\WEB-INF\\web.properties");
		Parameters params=new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder=new FileBasedConfigurationBuilder
		<FileBasedConfiguration>(PropertiesConfiguration.class).configure(params.properties().setFileName(fileDir));
		try {
			Configuration config=builder.getConfiguration();
			WebProperties.propertiesMap.put("projectRoot", servletContext.getRealPath(config.getString("projectName")));
			
			WebProperties.propertiesMap.put("projectName", config.getString("projectName"));
			WebProperties.propertiesMap.put("tempDir", config.getString("tempDir"));
			WebProperties.propertiesMap.put("headIconFileDefault", config.getString("headIconFileDefault"));
			WebProperties.propertiesMap.put("headIconDir", config.getString("headIconDir"));
			WebProperties.propertiesMap.put("headIconDirDefault", config.getString("headIconDirDefault"));
			WebProperties.propertiesMap.put("redirectTime", config.getString("redirectTime"));
			
			WebProperties.propertiesMap.put("ueditConfigJs", config.getString("ueditConfigJs"));
			WebProperties.propertiesMap.put("ueditJs", config.getString("ueditJs"));
			WebProperties.propertiesMap.put("ueditLang", config.getString("ueditLang"));
			
			NewsTypeService newsTypeService=new NewsTypeService();
			List<NewsType> newsTypes=new ArrayList<NewsType>();
			newsTypes=newsTypeService.getAll();
			this.getServletContext().setAttribute("newsTypes", newsTypes);
			
			AuthorityService authorityService=new AuthorityService();
			List<Authority> authoritys=authorityService.getAll();
			for(Authority authority:authoritys){
				String key;
				if(authority.getParam()==null||authority.getParam().isEmpty()){
					key=authority.getUrl()+authority.getUserType();
				}else{
					key=authority.getUrl()+authority.getParam()+authority.getUserType();
				}
				
				AuthorityTool.authorityMap.put(key, authority);
			}
			
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
