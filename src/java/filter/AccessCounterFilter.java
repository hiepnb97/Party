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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AccessCounterFilter là một Filter dùng để đếm số lần truy cập vào mỗi URL.
 * Filter này áp dụng cho tất cả các URL (/*) và sử dụng ConcurrentHashMap
 * để lưu trữ số lần truy cập một cách thread-safe.
 * Số lượt truy cập được lưu vào request attribute "visitCount".
 * 
 * @author hiepn
 */
@WebFilter(filterName="AccessCounterFilter", urlPatterns={"/*"})
public class AccessCounterFilter implements Filter {

    private static final boolean debug = true;

    /**
     * Đối tượng FilterConfig được sử dụng để cấu hình filter
     */
    private FilterConfig filterConfig = null;

    public AccessCounterFilter() {
    } 

    /**
     * Map lưu trữ số lần truy cập cho mỗi URL
     * Sử dụng ConcurrentHashMap và AtomicInteger để đảm bảo thread-safe
     */
    private static final Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();

    /**
     * Phương thức chính của filter, được gọi cho mỗi request
     * Tăng số lượt truy cập cho URL hiện tại và lưu vào request attribute
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

	// Ép kiểu request về HttpServletRequest để lấy URI
	HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Lấy đường dẫn URL của request hiện tại
        String path = httpRequest.getRequestURI();
        
        // Tăng số lượt truy cập cho URL hiện tại
        // Sử dụng computeIfAbsent để tạo mới AtomicInteger nếu chưa có
        // và incrementAndGet để tăng giá trị một cách thread-safe
        counter.computeIfAbsent(path, k -> new AtomicInteger(0)).incrementAndGet();

        // Lưu số lượt truy cập vào request attribute để JSP có thể truy cập
        request.setAttribute("visitCount", counter.get(path));

        // Cho phép request tiếp tục trong chuỗi filter
        chain.doFilter(request, response);
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
		log("AccessCounterFilter:Initializing filter");
	    }
	}
    }

    /**
     * Trả về chuỗi biểu diễn của filter
     * @return chuỗi biểu diễn của filter
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("AccessCounterFilter()");
	StringBuffer sb = new StringBuffer("AccessCounterFilter(");
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
