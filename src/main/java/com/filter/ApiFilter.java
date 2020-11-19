package com.filter;


import com.model.MyConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// Cấu trúc api luôn tuân thủ /api/v1/product, /api/v1/category....

/**
 * Mục đích của api sẽ tìm kiếm hoặc thao tác với các dữ liệu trong database:
 * + phải có kết nối với database
 * => Phải đảm bảo khi /api/ thì phải có connection thì mới thực hiện xử lí
 * thao tác với database để trẩ về kết quả tương ứng
 * => Ngoài chức năng định nghĩa, đây là trả về file json
 * thì ApiFilter còn có chức năng kiểm soát connection trước khi request đến servlet
 * + Để đảm bảo có Connection trước khi request đến servlet:
 * - Mỗi lần request vào api thì đều thực hiện kết nối lại
 * - Kiểm soát kết nối bằng cách: nếu connection đã tồn tại thì chuyển
 * đến servlet luôn, còn nếu chưa tồn tại thì mới tạo ra connection kết nối lại
 */
@WebFilter(filterName = "ApiFilter", urlPatterns = "/api/*")
public class ApiFilter implements Filter {

    private final MyConnection myConnection = new MyConnection();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        // Kiểm soát connection
        // Để kết nối đến DB thì sd hàm getConnect trong lớp MyConnection
        try {
            myConnection.connectDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
