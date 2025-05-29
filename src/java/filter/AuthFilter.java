/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */

package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * AuthFilter là một Filter dùng để kiểm tra xác thực người dùng.
 * Filter này sẽ chặn các request đến các URL cần xác thực (/delete, /edit, /add)
 * và kiểm tra xem người dùng đã đăng nhập hay chưa.
 * Nếu chưa đăng nhập, người dùng sẽ được chuyển hướng đến trang login.
 * 
 * @author hiepn
 */
@WebFilter(filterName="AuthFilter", urlPatterns={"/delete", "/edit", "/add"})
public class AuthFilter implements Filter {

    private static final boolean debug = true;

    /**
     * Đối tượng FilterConfig được sử dụng để cấu hình filter
     */
    private FilterConfig filterConfig = null;

    public AuthFilter() {
    } 

    /**
     * Phương thức chính của filter, được gọi cho mỗi request
     * Kiểm tra session và attribute "username" để xác định trạng thái đăng nhập
     * 
     * @param request The servlet request
     * @param response The servlet response
     * @param chain The filter chain
     * @throws IOException nếu có lỗi I/O
     * @throws ServletException nếu có lỗi servlet
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

	// Ép kiểu request và response về dạng HTTP
	HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        // Lấy session hiện tại (không tạo mới nếu chưa có)
        HttpSession session = req.getSession(false);
        
        // Kiểm tra session và attribute username
        if(session == null || session.getAttribute("username") == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang login
            res.sendRedirect("login");
        } else {
            // Nếu đã đăng nhập, cho phép request tiếp tục
            chain.doFilter(request, response);
        }
    }
    
    /**
     * Lấy đối tượng FilterConfig của filter này
     * @return FilterConfig object
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    /**
     * Thiết lập đối tượng FilterConfig cho filter
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    /**
     * Phương thức được gọi khi filter bị hủy
     */
    public void destroy() { 
    }

    /**
     * Phương thức khởi tạo filter
     * @param filterConfig The filter configuration object
     */
    public void init(FilterConfig filterConfig) { 
	// Lưu trữ FilterConfig để sử dụng sau này
	this.filterConfig = filterConfig;
	
	// Nếu debug mode được bật, ghi log khởi tạo
	if (filterConfig != null) {
	    if (debug) { 
		log("AuthFilter:Initializing filter");
	    }
	}
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("AuthFilter()");
	StringBuffer sb = new StringBuffer("AuthFilter(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
